package com.example.burny.imbur.di

import com.example.burny.imbur.di.gallery.GalleryViewModelModule
import com.example.burny.imbur.ui.MainActivity
import com.example.burny.imbur.ui.gallery.GalleryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewProviderModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

    @Scopes.GalleryScope
    @ContributesAndroidInjector(modules = [GalleryViewModelModule::class])
    abstract fun provideGalleryFragment(): GalleryFragment

}