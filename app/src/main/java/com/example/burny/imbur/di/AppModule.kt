package com.example.burny.imbur.di

import android.content.Context
import com.example.burny.imbur.ImburApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: ImburApplication): Context =
            application.applicationContext

}