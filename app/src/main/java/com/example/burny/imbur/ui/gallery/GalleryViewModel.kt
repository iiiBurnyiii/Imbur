package com.example.burny.imbur.ui.gallery

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.example.burny.imbur.R
import com.example.burny.imbur.data.GalleryRepository
import com.example.burny.imbur.utils.LoadStatus
import javax.inject.Inject

class GalleryViewModel @Inject constructor (
        val repository: GalleryRepository
) : ViewModel() {

    val sectionName = MutableLiveData<String>()
    private val result = map(sectionName) {
        repository.galleryOfSection(it, 10)
    }!!

    private val refreshStatus = switchMap(result) { it.refreshStatus }!!
    private val loadStatus = switchMap(result) { it.loadStatus }!!
    val isRefreshing = map(refreshStatus) { it == LoadStatus.LOADING }!!

    val gallery = switchMap(result) { it.galleryLiveData }!!
    val isGalleryEmpty = map(gallery) { it == null || it.size == 0 }!!

    val snackbarMessage = MutableLiveData<Int>()

    fun observeLoadStatus(lifecycleOwner: LifecycleOwner) {
        loadStatus.observe(lifecycleOwner, Observer { loadStatus ->
            when(loadStatus) {
                LoadStatus.ERROR -> onLoadStatusError()
            }
        })
    }

    fun refresh() {
        repository.refresh()
    }

    private fun onLoadStatusError() {
        retry()
        snackbarMessage.value = R.string.load_error
    }

    private fun retry() {
        repository.retryWhenConnect()
    }

    override fun onCleared() {
        super.onCleared()
        repository.clear()
    }

}
