package com.example.burny.imbur.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.example.burny.imbur.data.GalleryRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class GalleryViewModel @Inject constructor (
        val repository: GalleryRepository,
        @Named("GalleryCompositeDisposable")
        val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val sectionName = MutableLiveData<String>()
    val result= map(sectionName) {
        repository.galleryOfSection(it, 15)
    }

    val gallery = switchMap(result) {
        it.pagedList
    }!!
    val loadState = switchMap(result) {
        it.loadState
    }!!
    val refreshState = switchMap(result) {
        it.refreshState
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
