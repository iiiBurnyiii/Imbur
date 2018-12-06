package com.example.burny.imbur.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.burny.imbur.data.remote.ImgurApi
import com.example.burny.imbur.model.Album
import com.example.burny.imbur.utils.LoadStatus
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class GalleryDataSource(
        private val section: String,
        private val api: ImgurApi,
        private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Album>() {

    val initState = MutableLiveData<LoadStatus>()
    val loadState = MutableLiveData<LoadStatus>()

    private var retryCompletable: Completable? = null
    private var requestPage = 0

    fun retry() {
        retryCompletable?.let {
            compositeDisposable += retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( { },
                            {e ->
                                Log.d(GalleryDataSource.LOG_TAG, "Retry error: $e")
                            })
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {
        initState.postValue(LoadStatus.LOADING)
        loadState.postValue(LoadStatus.LOADING)
        Log.d(LOG_TAG, "Initial load, page: $requestPage")

        compositeDisposable += api.requestGallery(section, requestPage).subscribe(
                { gallery ->
                    setRetry(null)
                    initState.postValue(LoadStatus.LOADED)
                    loadState.postValue(LoadStatus.LOADED)

                    callback.onResult(gallery.data,null, ++requestPage)
                },
                { e: Throwable ->
                    initState.postValue(LoadStatus.ERROR)
                    loadState.postValue(LoadStatus.ERROR)
                    setRetry { invalidate() }

                    Log.e(LOG_TAG, "Initial load error: $e")
                }
        )

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        loadState.postValue(LoadStatus.LOADING)
        Log.d(LOG_TAG, "Load $requestPage page")

        compositeDisposable += api.requestGallery(section, params.key).subscribe(
                { gallery ->
                    setRetry(null)
                    loadState.postValue(LoadStatus.LOADED)

                    callback.onResult(gallery.data, ++requestPage)
                },
                { e: Throwable ->
                    loadState.postValue(LoadStatus.ERROR)

                    setRetry { loadAfter(params, callback) }
                    Log.e(LOG_TAG, "Load error on page $requestPage \n error: $e")
                }
        )

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        //игнорирем, так как загружаем список с начала.
    }

    private fun setRetry(action: (() -> Unit)?) {
        retryCompletable = if (action == null) {
            null
        } else {
            Completable.fromAction(Action(action))
        }
    }

    companion object {
        const val LOG_TAG = "GallerySourceLogger"
    }

}
