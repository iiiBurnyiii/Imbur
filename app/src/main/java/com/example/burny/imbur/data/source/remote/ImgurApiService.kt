package com.example.burny.imbur.data.source.remote

import com.example.burny.imbur.data.Album
import com.example.burny.imbur.data.Image
import com.example.burny.imbur.utils.Constants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ImgurApiService {

    @Headers("Authorization: Client-ID ${Constants.CLIENT_ID}")
    @GET("3/gallery/hot/0/?showViral=true&mature=true")
    fun getGallery(): Observable<ArrayList<Album>>

    @Headers("Authorization: Client-ID ${Constants.CLIENT_ID}")
    @GET("3/image/{id}")
    fun getImage(@Path("id") id: String): Observable<Image>

    companion object Factory {

        fun create(): ImgurApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.IMGUR_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(ImgurApiService::class.java)

        }

    }

}