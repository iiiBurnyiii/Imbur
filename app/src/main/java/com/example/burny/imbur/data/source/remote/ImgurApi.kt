package com.example.burny.imbur.data.source.remote

import android.util.Log
import com.example.burny.imbur.data.Gallery
import com.example.burny.imbur.utils.Constants
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ImgurApi {

//    @Headers("Authorization:Client-ID ${Constants.CLIENT_ID}")
    @GET("gallery/hot/{page}/?showViral=true&mature=true")
    fun requestHotGallery(@Path("page") page: Int): Single<Gallery>

    companion object {
        fun create(): ImgurApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("ApiLogger", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .addInterceptor { chain ->
                        val original = chain.request()

                        val request = original.newBuilder()
                                .header("Authorization", "Client-ID ${Constants.CLIENT_ID}")
                                .method(original.method(), original.body())
                                .build()

                        chain.proceed(request)
                    }
                    .build()

            return Retrofit.Builder()
                    .baseUrl(Constants.IMGUR_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ImgurApi::class.java)
        }
    }

}