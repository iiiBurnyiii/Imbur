package com.example.burny.imbur.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.burny.imbur.databinding.GalleryFragmentBinding
import com.example.burny.imbur.utils.setupSnackbar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.gallery_fragment.*
import javax.inject.Inject

class GalleryFragment : DaggerFragment() {

    private lateinit var binding: GalleryFragmentBinding

    @Inject lateinit var galleryAdapter: GalleryAdapter
    @Inject lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = GalleryFragmentBinding.inflate(inflater,
                container, false).apply {
            viewModel = ViewModelProviders.of(this@GalleryFragment, factory)[GalleryViewModel::class.java]
            setLifecycleOwner(this@GalleryFragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapter()
        binding.viewModel?.let { viewModel ->
            viewModel.sectionName.value = DEFAULT_SECTION_NAME
            viewModel.observeLoadStatus(this)
            view?.setupSnackbar(this, viewModel.snackbarMessage, Snackbar.LENGTH_LONG)
        }

    }

    private fun initAdapter() {
        with(rvGallery) {
            layoutManager = StaggeredGridLayoutManager(2, 1)
            adapter = galleryAdapter
        }
    }

    companion object {
        const val DEFAULT_SECTION_NAME = "hot"

        fun newInstance() = GalleryFragment()
    }

}
