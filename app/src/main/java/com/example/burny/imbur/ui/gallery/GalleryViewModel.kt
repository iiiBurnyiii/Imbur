package com.example.burny.imbur.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.burny.imbur.data.GalleryDataSourceFactory
import com.example.burny.imbur.data.to.Album
import java.util.concurrent.Executors
import javax.inject.Inject

class GalleryViewModel @Inject constructor (
        sourceFactory: GalleryDataSourceFactory
) : ViewModel() {

    val galleryList: LiveData<PagedList<Album>>
    val snackbarMessage = MutableLiveData<String>()

    private val pageSize = 15

    init {
        val pagedListConfig = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        galleryList = LivePagedListBuilder<Int, Album>(sourceFactory, pagedListConfig)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
    }

    fun refreshGallery() {
    }

    fun loadGallery() {
    }

    override fun onCleared() {
        super.onCleared()
    }

}
