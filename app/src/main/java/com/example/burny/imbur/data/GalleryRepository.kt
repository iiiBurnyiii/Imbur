package com.example.burny.imbur.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.toLiveData
import com.example.burny.imbur.data.remote.ImgurApi
import com.example.burny.imbur.data.source.GalleryDataSourceFactory
import com.example.burny.imbur.model.Album
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Inject

class GalleryRepository @Inject constructor (
        val api: ImgurApi,
        val context: Application
) {

    private lateinit var sourceFactory: GalleryDataSourceFactory
    private val compositeDisposable = CompositeDisposable()

    fun galleryOfSection(section: String, pageSize: Int): Listing<Album> {

        sourceFactory = GalleryDataSourceFactory(
                section = section,
                api = api,
                compositeDisposable = compositeDisposable
        )

        val livePagedList = sourceFactory.toLiveData(
                pageSize = pageSize,
                fetchExecutor = Executors.newSingleThreadExecutor()
        )

        return Listing (
                pagedList = livePagedList,
                loadState = switchMap(sourceFactory.sourceLiveData) {
                    it.loadState
                },
                refreshState = switchMap(sourceFactory.sourceLiveData) {
                    it.initState
                })
    }

    fun refresh() {
        sourceFactory.sourceLiveData.value?.invalidate()
    }

    fun retryWhenConnect() =
            ReactiveNetwork.observeNetworkConnectivity(context.applicationContext)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith( object : DisposableObserver<Connectivity>() {
                        override fun onComplete() {
                            this.dispose()
                        }

                        override fun onNext(connectivity: Connectivity) {
                            if (connectivity.available()) {
                                sourceFactory.sourceLiveData.value?.retry()
                                onComplete()
                            }
                        }

                        override fun onError(e: Throwable) {
                            Log.d(LOG_TAG, "Retry error: $e")
                        }
                    })

    fun clear() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    companion object {
        const val LOG_TAG = "GalleryRepositoryLogger"
    }

}