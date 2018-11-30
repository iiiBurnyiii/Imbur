package com.example.burny.imbur.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.burny.imbur.data.Album
import com.example.burny.imbur.di.Scopes
import javax.inject.Inject

@Scopes.GalleryScope
class GalleryDataSourceFactory @Inject constructor (
        val source: GalleryDataSource
) : DataSource.Factory<Int, Album>() {

    private val dataSourceLiveData = MutableLiveData<GalleryDataSource>()

    override fun create(): DataSource<Int, Album> {
        dataSourceLiveData.postValue(source)
        return source
    }

}