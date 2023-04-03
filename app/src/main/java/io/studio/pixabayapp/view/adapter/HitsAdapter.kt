package io.studio.pixabayapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import io.studio.pixabayapp.R
import io.studio.pixabayapp.databinding.HitItemViewBinding
import io.studio.pixabayapp.view.model.HitDiffUtil
import io.studio.pixabayapp.view.model.HitUiModel

class HitsAdapter(private val onHitClicked: (HitUiModel) -> Unit) :
    ListAdapter<HitUiModel, HitsAdapter.HitViewHolder>(HitDiffUtil) {

    inner class HitViewHolder(private val binding: HitItemViewBinding) : ViewHolder(binding.root) {
        fun bind(item: HitUiModel) {
            binding.username.text = item.username
            binding.thumbnail.load(item.thumbnail) {
                crossfade(true)
                placeholder(R.drawable.ic_place_holder)
            }
            binding.tags.text = item.tags
            itemView.setOnClickListener {
                onHitClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitViewHolder {
        val binding = HitItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HitViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}