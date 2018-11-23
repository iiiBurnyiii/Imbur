package com.example.burny.imbur.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.burny.imbur.databinding.GalleryFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GalleryFragment : DaggerFragment() {

    private lateinit var binding: GalleryFragmentBinding

    @Inject lateinit var galleryAdapter: GalleryAdapter
    @Inject lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = GalleryFragmentBinding.inflate(inflater,
                container, false).apply {
            viewModel = ViewModelProviders.of(this@GalleryFragment, factory)[GalleryViewModel::class.java]
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.viewModel?.loadGallery()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupAdapter()
    }

    private fun setupAdapter() =
            with(binding.rvGallery) {
                layoutManager = StaggeredGridLayoutManager(2, 1)
                adapter = galleryAdapter
            }

    companion object {
        fun newInstance() = GalleryFragment()
    }

}
