package com.example.burny.imbur.data.remote

import android.util.Log
import com.example.burny.imbur.model.Gallery
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ImgurApi {

    @GET("gallery/{section}/{page}/?showViral=true&mature=true")
    fun requestGallery(@Path("section") section: String,
                       @Path("page") page: Int): Single<Gallery>

    companion object {
        private const val CLIENT_ID = "7ff93026edca660"
        private const val BASE_URL = "https://api.imgur.com/3/"

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
                                .header("Authorization", "Client-ID ${CLIENT_ID}")
                                .method(original.method(), original.body())
                                .build()

                        chain.proceed(request)
                    }
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ImgurApi::class.java)
        }
    }

}