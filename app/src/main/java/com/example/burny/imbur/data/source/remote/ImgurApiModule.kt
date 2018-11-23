package com.example.burny.imbur.data.source.remote

import com.example.burny.imbur.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ImgurApiModule {

    @Provides
    @Singleton
    fun provideApiClient(): Retrofit = Retrofit.Builder()
            .baseUrl(Constants.IMGUR_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGalleryService(retrofit: Retrofit): GalleryService =
            retrofit.create(GalleryService::class.java)

}