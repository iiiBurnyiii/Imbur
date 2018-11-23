package com.example.burny.imbur.data.source.remote

import com.example.burny.imbur.data.Gallery
import com.example.burny.imbur.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GalleryService {

    @Headers("Authorization:Client-ID ${Constants.CLIENT_ID}")
    @GET("gallery/hot/{page}/?showViral=true&mature=true")
    fun requestHotGallery(@Path("page") page: Int): Single<Gallery>


}