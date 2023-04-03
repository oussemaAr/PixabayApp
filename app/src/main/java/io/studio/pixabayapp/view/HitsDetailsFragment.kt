package io.studio.pixabayapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import io.studio.pixabayapp.R
import io.studio.pixabayapp.common.ui.createChip
import io.studio.pixabayapp.databinding.HitDetailsFragmentBinding

class HitsDetailsFragment : Fragment() {

    private lateinit var binding: HitDetailsFragmentBinding

    private val hitsArgs: HitsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HitDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hit = hitsArgs.hit
        with(binding) {
            image.load(hit.image) {
                crossfade(true)
                placeholder(R.drawable.ic_place_holder)
            }
            username.text = hit.username
            commentCount.text = getString(R.string.total_comment, hit.commentCount)
            likeCount.text = getString(R.string.total_like, hit.likesCount)
            downloadCount.text = getString(R.string.total_donwload, hit.downloadCount)
            hit.tags.split(",")
                .map { it.trim() }
                .forEach {
                binding.tags.addView(requireContext().createChip(it))
            }
        }
    }
}