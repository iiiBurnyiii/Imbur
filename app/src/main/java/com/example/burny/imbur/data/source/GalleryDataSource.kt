package com.example.burny.imbur.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.burny.imbur.data.remote.ImgurApi
import com.example.burny.imbur.model.Album
import com.example.burny.imbur.utils.LoadState
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

    val initState = MutableLiveData<LoadState>()
    val loadState = MutableLiveData<LoadState>()

    private var retryCompletable: Completable? = null
    private var requestPage = 0

    fun retry() {
        retryCompletable?.let {
            compositeDisposable += retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( { setRetry(null) },
                            {e ->
                                Log.d(GalleryDataSource.LOG_TAG, "Retry error: $e")
                            })
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {
        Log.d(LOG_TAG, "Initial load, page: $requestPage")
        initState.postValue(LoadState.LOADING)
        loadState.postValue(LoadState.LOADING)

        compositeDisposable += api.requestGallery(section, requestPage).subscribe(
                { gallery ->
                    setRetry(null)
                    initState.postValue(LoadState.LOADED)
                    loadState.postValue(LoadState.LOADED)

                    callback.onResult(gallery.data,null, ++requestPage)
                },
                { e: Throwable ->
                    Log.e(LOG_TAG, "Initial load error: $e")
                    initState.postValue(LoadState.ERROR)
                    loadState.postValue(LoadState.ERROR)

                    setRetry { loadInitial(params, callback) }
                }
        )

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        Log.d(LOG_TAG, "Load $requestPage page")
        loadState.postValue(LoadState.LOADING)

        compositeDisposable += api.requestGallery(section, params.key).subscribe(
                { gallery ->
                    setRetry(null)
                    loadState.postValue(LoadState.LOADED)

                    callback.onResult(gallery.data, ++requestPage)
                },
                { e: Throwable ->
                    Log.e(LOG_TAG, "Load error on page $requestPage \n error: $e")
                    loadState.postValue(LoadState.ERROR)

                    setRetry { loadAfter(params, callback) }
                }
        )

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        //игнорирем, так как загружаем список с начала.
    }

    override fun invalidate() {
        super.invalidate()
        Log.d(LOG_TAG, "invalidate()")
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
