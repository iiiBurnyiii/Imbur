package com.example.burny.imbur.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.burny.imbur.data.to.Album
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryDataSourceFactory @Inject constructor (
        val source: GalleryDataSource
) : DataSource.Factory<Int, Album>() {

    private val dataSourceLiveData = MutableLiveData<GalleryDataSource>()

    override fun create(): DataSource<Int, Album> {
        dataSourceLiveData.postValue(source)
        return source
    }

}