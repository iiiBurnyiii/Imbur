package com.example.burny.imbur.di

import android.content.Context
import com.example.burny.imbur.ImburApplication
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: ImburApplication): Context =
            application.applicationContext

    @Provides
    @Singleton
    fun provideReactiveNetwork(context: Context): Observable<Connectivity> =
            ReactiveNetwork.observeNetworkConnectivity(context.applicationContext)

}