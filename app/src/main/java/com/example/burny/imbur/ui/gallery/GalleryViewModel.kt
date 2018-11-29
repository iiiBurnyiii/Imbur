package com.example.burny.imbur.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.burny.imbur.data.GalleryDataSourceFactory
import com.example.burny.imbur.data.to.Album
import javax.inject.Inject

class GalleryViewModel @Inject constructor (
        sourceFactory: GalleryDataSourceFactory
) : ViewModel() {

    var galleryList: LiveData<PagedList<Album>>

    private val pageSize = 20

    init {
        val pagedListConfig = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        galleryList = LivePagedListBuilder<Int, Album>(sourceFactory, pagedListConfig).build()
    }

    fun refreshGallery() {
    }

    fun loadGallery() {
    }

    override fun onCleared() {
        super.onCleared()
    }

}
