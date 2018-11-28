package com.example.burny.imbur.data.source.remote

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImgurApiModule {

    @Provides
    @Singleton
    fun provideApiClient(): ImgurApi = ImgurApi.create()

}