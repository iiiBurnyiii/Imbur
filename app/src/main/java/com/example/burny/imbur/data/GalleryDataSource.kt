package com.example.burny.imbur.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.burny.imbur.data.remote.ImgurApi
import com.example.burny.imbur.data.to.Album
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryDataSource @Inject constructor (
        var api: ImgurApi
) : PageKeyedDataSource<Int, Album>() {

    private val compositeDisposable = CompositeDisposable()

    private var requestPage = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {
        Log.d("DataSourceLogger", "Load first page: $requestPage")

        compositeDisposable += api.requestGallery("hot", requestPage).subscribe(
                { gallery ->
                    callback.onResult(gallery.data, null, requestPage++)
                },
                { e: Throwable ->
                    requestPage = 0
                    Log.e("DataSourceLogger", "initial load error: $e")
                }
        )

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        Log.d("DataSourceLogger", "Load next page: $requestPage")

        compositeDisposable += api.requestGallery("hot", params.key).subscribe(
                { gallery ->
                    callback.onResult(gallery.data, requestPage++)
                },
                { e: Throwable ->
                    requestPage = 0
                    Log.e("DataSourceLogger", "load after error: $e")
                }
        )

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        //игнорирем, так как загружаем список с начала.
    }

}
