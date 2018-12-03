package com.example.burny.imbur.ui.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.example.burny.imbur.model.Album
import com.example.burny.imbur.ui.gallery.GalleryAdapter

object InitPagedList {

    @JvmStatic
    @BindingAdapter("items")
    fun RecyclerView.setItems(data: PagedList<Album>?) {
        with(this.adapter as GalleryAdapter) {
            submitList(data)
        }
    }

}