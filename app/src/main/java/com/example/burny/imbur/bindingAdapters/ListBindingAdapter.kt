package com.example.burny.imbur.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.burny.imbur.data.Album
import com.example.burny.imbur.ui.gallery.GalleryAdapter

class ListBindingAdapter {

    @BindingAdapter("items")
    fun RecyclerView.setListItems(items: ArrayList<Album>) {
        with(this.adapter as GalleryAdapter) {
            replaceData(items)
        }
    }

}