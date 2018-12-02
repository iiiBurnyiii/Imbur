package com.example.burny.imbur.ui

import com.example.burny.imbur.di.Scopes
import com.example.burny.imbur.di.gallery.GalleryFragmentModule
import com.example.burny.imbur.di.gallery.GalleryViewModelModule
import com.example.burny.imbur.ui.gallery.GalleryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewProviderModule {

    @ContributesAndroidInjector
    abstract fun provideAppActivity(): AppActivity

    @Scopes.GalleryScope
    @ContributesAndroidInjector(modules = [GalleryFragmentModule::class, GalleryViewModelModule::class])
    abstract fun provideGalleryFragment(): GalleryFragment

}