package com.example.burny.imbur.data.source.local

import com.example.burny.imbur.data.Album
import com.example.burny.imbur.data.source.DataSource
import io.reactivex.Observable

class CachedDataSource : DataSource {

    override fun getGallery(): Observable<ArrayList<Album>> {
        val cache = ArrayList<Album>()

//        with(cache){
//            add(Album("1", title = "Album Title", cover = "https://i.imgur.com/FYrcEMe.jpg", coverWidth = 4939, coverHeight = 4940, points = 300))
//            add(Album("2", title = "Album Title", cover = "https://i.imgur.com/sq3KgEt.jpg", coverWidth = 1481, coverHeight = 1481, points = 50000))
//            add(Album("3", title = "Album Title", cover = "https://i.imgur.com/BYIhGxY.jpg", coverWidth = 2175, coverHeight = 2175, points = 2))
//            add(Album("4", title = "Album Title", cover = "https://i.imgur.com/AVoRai6.jpg", coverWidth = 1713, coverHeight = 1621, points = 900_000_000))
//            add(Album("5", title = "Album Title", cover = "https://i.imgur.com/vTidu4w.jpg", coverWidth = 1078, coverHeight = 1626, points = 0))
//            add(Album("6", title = "Album Title", cover = "https://i.imgur.com/PIKpxVc.jpg", coverWidth = 637, coverHeight = 604, points = 358))
//            add(Album("7", title = "Album Title", cover = "https://i.imgur.com/XpDDcpJ.jpg", coverWidth = 700, coverHeight = 475, points = 300))
//        }

        return Observable.just(cache)
    }

    override fun getAlbum(id: String): Observable<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}