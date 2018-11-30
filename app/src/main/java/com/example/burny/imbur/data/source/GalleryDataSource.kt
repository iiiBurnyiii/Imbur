package com.example.burny.imbur.data.source

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.burny.imbur.data.Album
import com.example.burny.imbur.data.remote.ImgurApi
import com.example.burny.imbur.di.Scopes
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject
import javax.inject.Named

@Scopes.GalleryScope
class GalleryDataSource @Inject constructor (
        val api: ImgurApi,
        @Named("GalleryCompositeDisposable")
        val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Album>() {

    private var requestPage = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {
        Log.d("DataSourceLogger", "Load first page: $requestPage")

        compositeDisposable += api.requestGallery("hot", requestPage).subscribe(
                { gallery ->
                    callback.onResult(gallery.data, null, requestPage++)
                },
                { e: Throwable ->
                    Log.e("DataSourceLogger", "init load error: $e")
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
                    Log.e("DataSourceLogger", "load error on ${requestPage - 1} page \n error: $e")
                }
        )

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        //игнорирем, так как загружаем список с начала.
    }

}
