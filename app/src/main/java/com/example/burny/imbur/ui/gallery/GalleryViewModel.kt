package com.example.burny.imbur.ui.gallery

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.burny.imbur.data.Album
import com.example.burny.imbur.data.source.GalleryRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GalleryViewModel @Inject constructor (val repository: GalleryRepository) : ViewModel() {

    val isLoading = ObservableBoolean(false)
    var gallery = ObservableArrayList<Album>()

    private var disposable = CompositeDisposable()
    private var page = 0

    fun refreshGallery() {
        gallery.clear()
        loadGallery()
    }

    fun loadGallery() {
        isLoading.set(true)
        disposable.add( repository.getGallery(page)
                .subscribeOn(Schedulers.io())
                .subscribe({ data: Album? ->
                    gallery.add(data)
                    Log.d("viewModelLogging", "data: $data")
                }, { e: Throwable? ->
                    Log.e("viewModelLogging", "loadGallery error: $e")
                }, { //onComplete
                    isLoading.set(false)
                }))
    }

    override fun onCleared() {

        if (!disposable.isDisposed) {
            gallery.clear()
            disposable.dispose()
        }

        super.onCleared()
    }

}
