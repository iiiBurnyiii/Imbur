package com.example.burny.imbur.di

import android.app.Application
import com.example.burny.imbur.ImburApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: ImburApplication): Application =
            application

}