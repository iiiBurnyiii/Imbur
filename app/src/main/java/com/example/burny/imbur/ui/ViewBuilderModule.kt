package com.example.burny.imbur.ui

import com.example.burny.imbur.di.Scopes
import com.example.burny.imbur.ui.gallery.GalleryFragment
import com.example.burny.imbur.ui.gallery.GalleryModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideAppActivity(): AppActivity

    @Scopes.GalleryScope
    @ContributesAndroidInjector(modules = [GalleryModule::class])
    abstract fun provideGalleryFragment(): GalleryFragment

}