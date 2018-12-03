package com.example.burny.imbur.di

import com.example.burny.imbur.ImburApplication
import com.example.burny.imbur.di.api.ImgurApiModule
import com.example.burny.imbur.di.vmFactory.ViewModelFactoryModule
import com.example.burny.imbur.ui.ViewProviderModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewProviderModule::class,
    ViewModelFactoryModule::class,
    ImgurApiModule::class
])
interface AppComponent : AndroidInjector<ImburApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ImburApplication>()

}