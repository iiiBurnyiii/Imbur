package com.example.burny.imbur.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.burny.imbur.di.ViewModelKey
import com.example.burny.imbur.ui.gallery.GalleryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel::class)
    abstract fun bindGalleryViewModel(galleryViewModel: GalleryViewModel): ViewModel

}

