package com.example.burny.imbur.ui.gallery

import androidx.lifecycle.ViewModel
import com.example.burny.imbur.di.Scopes
import com.example.burny.imbur.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GalleryViewModelModule {

    @Binds
    @IntoMap
    @Scopes.GalleryScope
    @ViewModelKey(GalleryViewModel::class)
    abstract fun bindGalleryViewModel(galleryViewModel: GalleryViewModel): ViewModel

}
