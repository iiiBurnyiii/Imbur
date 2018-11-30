package com.example.burny.imbur.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.burny.imbur.data.Album
import com.example.burny.imbur.data.source.GalleryDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Named

class GalleryViewModel @Inject constructor (
        sourceFactory: GalleryDataSourceFactory,
        @Named("GalleryCompositeDisposable")
        val compositeDisposable: CompositeDisposable
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
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }

}
