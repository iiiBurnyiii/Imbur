package com.example.burny.imbur.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.burny.imbur.data.remote.ImgurApi
import com.example.burny.imbur.model.Album
import javax.inject.Inject

class GalleryDataSourceFactory @Inject constructor (
        api: ImgurApi
) : DataSource.Factory<Int, Album>() {

    val sourceLiveData = MutableLiveData<GalleryDataSource>()

    override fun create(): DataSource<Int, Album> {
        sourceLiveData.postValue(source)
        return source
    }

}