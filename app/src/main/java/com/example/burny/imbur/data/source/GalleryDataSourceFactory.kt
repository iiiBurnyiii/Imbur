package com.example.burny.imbur.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.burny.imbur.data.remote.ImgurApi
import com.example.burny.imbur.model.Album
import io.reactivex.disposables.CompositeDisposable

class GalleryDataSourceFactory(
        private val section: String,
        private val api: ImgurApi,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Album>() {

    val sourceLiveData = MutableLiveData<GalleryDataSource>()

    override fun create(): DataSource<Int, Album> {
        val source = GalleryDataSource(section, api, compositeDisposable)
        sourceLiveData.postValue(source)
        return source
    }

}