package com.example.burny.imbur.di

import android.content.Context
import com.example.burny.imbur.ImburApplication
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: ImburApplication): Context =
            application.applicationContext

    @Provides
    @Singleton
    fun providePicasso(): Picasso = Picasso.get()

}