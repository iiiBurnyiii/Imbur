package com.example.burny.imbur.data.source

import com.example.burny.imbur.data.Album
import io.reactivex.Observable
import javax.inject.Inject

//Проверяем, если данные есть в кеше, то береи их оттуда
// или загружаем по api и кладем в кеш
class GalleryRepository @Inject constructor() : DataSource {

    private val cache = CachedDataSource()

    override fun getGallery(): Observable<ArrayList<Album>> {
        return cache.getGallery()
    }

    override fun getAlbum(id: String): Observable<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}