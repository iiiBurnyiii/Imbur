package com.example.burny.imbur.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.burny.imbur.data.remote.ImgurApi
import com.example.burny.imbur.model.Album
import com.example.burny.imbur.utils.LoadState
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class GalleryDataSource @Inject constructor (
        val api: ImgurApi,
        val rxNetwork: Observable<Connectivity>,
        @Named("GalleryCompositeDisposable")
        val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Album>() {

    private var retryCompletable: Completable? = null
    private var requestPage = 0
    var section = ""

    val initLoadState = MutableLiveData<LoadState>()
    val loadState = MutableLiveData<LoadState>()

    //
    fun retryWhenConnect() {
        retryCompletable?.let {
            compositeDisposable += rxNetwork
                    .switchMapCompletable { retryCompletable }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( {  },
                            { e ->
                                Log.e(LOG_TAG, "Error in retryWhenConnect(): $e")
                            })
        }
    }

    override fun invalidate() {
        Log.d(LOG_TAG, "invalidate")
        requestPage = 0
        super.invalidate()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {
        Log.d(LOG_TAG, "Initial load, page: $requestPage")

        loadState.postValue(LoadState.LOADING)
        initLoadState.postValue(LoadState.LOADING)

        compositeDisposable += api.requestGallery("hot", requestPage).subscribe(
                { gallery ->
                    setRetry(null)

                    loadState.postValue(LoadState.LOADED)
                    initLoadState.postValue(LoadState.LOADED)

                    callback.onResult(gallery.data, null, ++requestPage)
                },
                { e: Throwable ->
                    Log.e(LOG_TAG, "Initial load error: $e")

                    setRetry {
                        loadInitial(params, callback)
                    }

                    loadState.postValue(LoadState.ERROR)
                    initLoadState.postValue(LoadState.ERROR)

                }
        )

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        Log.d(LOG_TAG, "Load $requestPage page")
        loadState.postValue(LoadState.LOADING)

        compositeDisposable += api.requestGallery("hot", params.key).subscribe(
                { gallery ->
                    setRetry(null)

                    loadState.postValue(LoadState.LOADED)

                    callback.onResult(gallery.data, ++requestPage)
                },
                { e: Throwable ->
                    Log.e(LOG_TAG, "Load error on page $requestPage \n error: $e")

                    setRetry {
                        loadAfter(params, callback)
                    }

                    loadState.postValue(LoadState.ERROR)

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
