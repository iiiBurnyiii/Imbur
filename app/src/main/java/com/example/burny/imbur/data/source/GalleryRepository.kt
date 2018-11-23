package com.example.burny.imbur.data.source

import com.example.burny.imbur.data.Album
import com.example.burny.imbur.data.source.remote.RemoteGalleryDataSource
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GalleryRepository @Inject constructor(
        val remoteGalleryDataSource: RemoteGalleryDataSource
) : DataSource {

    override fun getGallery(page: Int): Flowable<Album> {
        return remoteGalleryDataSource.requestGallery(page).flattenAsFlowable { gallery ->
            gallery.data
        }
//        return CachedDataSource().getGallery().flattenAsFlowable { gallery ->
//            gallery.data
//        }
    }

    override fun getAlbum(id: String): Single<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}