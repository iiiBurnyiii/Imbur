package com.example.burny.imbur.ui.gallery

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.burny.imbur.data.Album
import com.example.burny.imbur.data.source.GalleryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GalleryViewModel @Inject constructor (val repository: GalleryRepository) : ViewModel() {

    val isLoading = ObservableBoolean(false)
    var gallery = MutableLiveData<ArrayList<Album>>()

    private var disposable = CompositeDisposable()

    fun loadGallery() {
        isLoading.set(true)
        disposable.add( repository.getGallery()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith( object : DisposableObserver<ArrayList<Album>>() {

                    override fun onComplete() {
                        isLoading.set(false)
                    }

                    override fun onNext(data: ArrayList<Album>) {
                        gallery.value = data
                    }

                    override fun onError(e: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                }))
    }

    fun reloadGallery() {
        clearGallery()
        loadGallery()
    }

    private fun clearGallery() {
        gallery.value = null
    }

    override fun onCleared() {

        if (!disposable.isDisposed) {
            clearGallery()
            disposable.dispose()
        }

        super.onCleared()
    }

}
