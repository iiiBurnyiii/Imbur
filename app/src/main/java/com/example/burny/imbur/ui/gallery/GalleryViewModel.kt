package com.example.burny.imbur.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.example.burny.imbur.data.GalleryRepository
import com.example.burny.imbur.utils.LoadState
import javax.inject.Inject

class GalleryViewModel @Inject constructor (
        val repository: GalleryRepository
) : ViewModel() {

    val sectionName = MutableLiveData<String>()
    private val result = map(sectionName) {
        repository.galleryOfSection(it, 15)
    }!!

    private val refreshState = switchMap(result) { it.refreshState }!!
    val isRefreshing = map(refreshState) { it == LoadState.LOADING }!!
    val loadState = switchMap(result) { it.loadState }!!
    val isLoading = map(loadState) { it == LoadState.LOADING }!!

    val gallery = switchMap(result) { it.pagedList }!!
    val isGalleryEmpty = map(gallery) { it == null || it.size == 0 }!!

    fun refresh() {
        repository.refresh()
    }

    fun retry() {
        repository.retryWhenConnect()
    }

    override fun onCleared() {
        super.onCleared()
        repository.clear()
    }

    companion object {
        const val LOG_TAG = "ViewModelLogger"
    }

}
