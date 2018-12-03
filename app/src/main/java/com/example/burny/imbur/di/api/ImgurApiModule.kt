package com.example.burny.imbur.di.api

import com.example.burny.imbur.data.remote.ImgurApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImgurApiModule {

    @Provides
    @Singleton
    fun provideApiClient(): ImgurApi = ImgurApi.create()

}