package com.example.burny.imbur.data.source

import com.example.burny.imbur.data.Album
import io.reactivex.Flowable
import io.reactivex.Single

interface IGalleryRepository {

    fun getGallery(page: Int): Flowable<Album>

    fun getAlbum(id: String): Single<Album>

}