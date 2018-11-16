package com.example.burny.imbur.data.source

import com.example.burny.imbur.data.Album
import io.reactivex.Observable

interface DataSource {

    fun getGallery(): Observable<ArrayList<Album>>

    fun getAlbum(id: String): Observable<Album>

}