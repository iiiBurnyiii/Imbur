package com.example.burny.imbur.ui

import com.example.burny.imbur.di.Scopes
import com.example.burny.imbur.ui.gallery.GalleryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewProviderModule {

    @ContributesAndroidInjector
    abstract fun provideAppActivity(): AppActivity

    @Scopes.GalleryScope
    @ContributesAndroidInjector
    abstract fun provideGalleryFragment(): GalleryFragment

}