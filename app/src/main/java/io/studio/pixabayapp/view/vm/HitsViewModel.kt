package io.studio.pixabayapp.view.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.studio.pixabayapp.domain.FetchHitsUseCase
import io.studio.pixabayapp.domain.LoadHitsUseCase
import io.studio.pixabayapp.view.mapper.toUiList
import io.studio.pixabayapp.view.model.HitUiModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HitsViewModel @Inject constructor(
    private val fetchHitsUseCase: FetchHitsUseCase,
    private val loadHitsUseCase: LoadHitsUseCase
) : ViewModel() {

    private var currentQuery = "fruits"

    @OptIn(FlowPreview::class)
    val hits: StateFlow<List<HitUiModel>> = loadHitsUseCase()
        .map { it.toUiList() }
        .debounce(300)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    init {
        fetchHits()
    }

    private fun fetchHits() = viewModelScope.launch {
        fetchHitsUseCase(currentQuery)
    }

    fun search(query: String) {
        currentQuery = query
        fetchHits()
    }
}