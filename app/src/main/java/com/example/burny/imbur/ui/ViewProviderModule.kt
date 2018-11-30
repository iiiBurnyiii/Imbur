package com.example.burny.imbur.ui

import com.example.burny.imbur.di.Scopes
import com.example.burny.imbur.ui.gallery.GalleryFragment
import com.example.burny.imbur.ui.gallery.GalleryFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewProviderModule {

    @ContributesAndroidInjector
    abstract fun provideAppActivity(): AppActivity

    @Scopes.GalleryScope
    @ContributesAndroidInjector(modules = [GalleryFragmentModule::class])
    abstract fun provideGalleryFragment(): GalleryFragment

}