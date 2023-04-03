package io.studio.pixabayapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import io.studio.pixabayapp.R
import io.studio.pixabayapp.databinding.HitsListFragmentBinding
import io.studio.pixabayapp.view.adapter.HitsAdapter
import io.studio.pixabayapp.view.vm.HitsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListHitsFragment : Fragment() {

    private val viewModel: HitsViewModel by viewModels()

    private lateinit var binding: HitsListFragmentBinding

    private val hitsAdapter: HitsAdapter = HitsAdapter {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.hit_click_title)
            .setMessage(R.string.hit_click_message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.hit_click_positive_action)) { _, _ ->
                val action =
                    ListHitsFragmentDirections.actionListHitsViewToHitsDetailsFragment(it)
                findNavController().navigate(action)
            }
            .setNegativeButton(getString(R.string.hit_click_negative_action)) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HitsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hitsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(false)
            adapter = hitsAdapter
        }

        binding.query.doAfterTextChanged { text -> viewModel.search(text.toString()) }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.hits.collectLatest { hits ->
                    hitsAdapter.submitList(hits)
                }
            }
        }
    }
}
