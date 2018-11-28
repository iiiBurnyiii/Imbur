package com.example.burny.imbur.data.source

import com.example.burny.imbur.data.Album
import com.example.burny.imbur.data.source.remote.ImgurApi
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GalleryRepository @Inject constructor(
        val api: ImgurApi
) : IGalleryRepository {

    override fun getGallery(page: Int): Flowable<Album> {
        return api.requestHotGallery(page).flattenAsFlowable { gallery ->
            gallery.data
        }
    }

    override fun getAlbum(id: String): Single<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}