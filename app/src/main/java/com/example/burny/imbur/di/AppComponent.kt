package com.example.burny.imbur.di

import com.example.burny.imbur.ImburApplication
import com.example.burny.imbur.ui.ViewBuilderModule
import com.example.burny.imbur.utils.ViewModelFactoryModule
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewBuilderModule::class,
    ViewModelFactoryModule::class
])
interface AppComponent {

    fun inject(instance: ImburApplication?)
    fun picasso(): Picasso

}