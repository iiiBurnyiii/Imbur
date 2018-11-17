package com.example.burny.imbur.ui.gallery

import com.example.burny.imbur.di.Scopes
import dagger.Module
import dagger.Provides

@Module
class GalleryModule {

    @Provides
    @Scopes.GalleryScope
    fun provideGalleryFragment(): GalleryFragment = GalleryFragment()

    @Provides
    @Scopes.GalleryScope
    fun provideGalleryAdapter(): GalleryAdapter = GalleryAdapter()

}