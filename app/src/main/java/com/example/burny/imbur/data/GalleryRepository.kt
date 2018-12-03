package com.example.burny.imbur.data

import android.content.Context
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.burny.imbur.data.remote.ImgurApi
import com.example.burny.imbur.data.source.GalleryDataSourceFactory
import com.example.burny.imbur.model.Album
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Named

class GalleryRepository @Inject constructor (
        val api: ImgurApi,
        val context: Context,
        @Named("GalleryCompositeDisposable")
        val compositeDisposable: CompositeDisposable
) {

    private lateinit var sourceFactory: GalleryDataSourceFactory

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

    fun retry() {
        sourceFactory.sourceLiveData.value?.retry()
    }

    private val boundaryCallback =
            object : PagedList.BoundaryCallback<Album>() {
                override fun onItemAtEndLoaded(itemAtEnd: Album) {
                    //init snackbar
                }

                override fun onZeroItemsLoaded() {
                    //init placeholder
                }

            }

    companion object {
        const val LOG_TAG = "GalleryRepositoryLogger"
    }

}