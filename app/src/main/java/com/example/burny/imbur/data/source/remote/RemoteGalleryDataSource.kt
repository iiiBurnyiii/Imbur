package com.example.burny.imbur.data.source.remote

import com.example.burny.imbur.data.Album
import com.example.burny.imbur.data.Gallery
import io.reactivex.Single
import javax.inject.Inject

class RemoteGalleryDataSource @Inject constructor(
        val galleryService: GalleryService
) {

    fun requestGallery(page: Int): Single<Gallery> =
            galleryService.requestHotGallery(page)

    fun requestAlbum(id: String): Single<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}