package com.example.burny.imbur.data

import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.burny.imbur.data.remote.ImgurApi
import com.example.burny.imbur.data.source.GalleryDataSourceFactory
import com.example.burny.imbur.model.Album
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Named

class GalleryRepository @Inject constructor (
        val api: ImgurApi,
        val rxNetwork: ReactiveNetwork,
        @Named("GalleryCompositeDisposable")
        val compositeDisposable: CompositeDisposable
) {

    private lateinit var sourceFactory: GalleryDataSourceFactory

    fun galleryOfSection(section: String, pageSize: Int): Listing<Album> {

        sourceFactory = GalleryDataSourceFactory()

        val source = sourceFactory.sourceLiveData.value
        source?.section = section

        val livePagedList = sourceFactory.toLiveData(
                pageSize = pageSize,
                fetchExecutor = Executors.newSingleThreadExecutor()
        )

        val loadState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.loadState
        }
        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initLoadState
        }

        return Listing (
                pagedList = livePagedList,
                loadState = loadState,
                refreshState = refreshState)

    }

    fun refresh() {
        sourceFactory.sourceLiveData.value?.invalidate()
    }

    fun retry() {
        sourceFactory.sourceLiveData.value?.retryWhenConnect()
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

}