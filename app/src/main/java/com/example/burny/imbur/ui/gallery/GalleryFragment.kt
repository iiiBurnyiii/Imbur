package com.example.burny.imbur.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.burny.imbur.R
import com.example.burny.imbur.databinding.GalleryFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GalleryFragment : DaggerFragment() {

    private lateinit var binding: GalleryFragmentBinding

    @Inject lateinit var adapter: GalleryAdapter
    @Inject lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.gallery_fragment,
                container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, factory)[GalleryViewModel::class.java]
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.rvGallery.layoutManager = StaggeredGridLayoutManager(2, 1)
        binding.rvGallery.adapter = adapter
        viewModel.gallery.observe(this,
                Observer { it?.let { data -> adapter.replaceData(data) } })

    }

    companion object {

        fun newInstance(): GalleryFragment = GalleryFragment()

    }

}
