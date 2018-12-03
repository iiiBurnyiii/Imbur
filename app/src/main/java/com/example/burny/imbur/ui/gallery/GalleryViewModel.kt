package com.example.burny.imbur.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.example.burny.imbur.data.GalleryRepository
import com.example.burny.imbur.utils.LoadState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class GalleryViewModel @Inject constructor (
        val repository: GalleryRepository,
        @Named("GalleryCompositeDisposable")
        val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val sectionName = MutableLiveData<String>()
    private val result= map(sectionName) {
        repository.galleryOfSection(it, 15)
    }!!
    private val refreshState = switchMap(result) {
        it.refreshState
    }!!

    val gallery = switchMap(result) {
        it.pagedList
    }!!
    val loadState = switchMap(result) {
        it.loadState
    }!!

    val isGalleryEmpty = map(gallery) {
        it == null || it.size == 0
    }!!
    val isRefreshing = map(refreshState) {
        it == LoadState.LOADING
    }!!


    fun refresh() {
        repository.refresh()
    }

    fun retry() {
        repository.retry()
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }

    companion object {
        const val LOG_TAG = "ViewModelLogger"
    }

}
