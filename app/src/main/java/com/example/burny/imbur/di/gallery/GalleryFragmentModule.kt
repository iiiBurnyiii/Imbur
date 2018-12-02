package com.example.burny.imbur.di.gallery

import com.example.burny.imbur.di.Scopes
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Named

@Module
class GalleryFragmentModule {

    @Provides
    @Scopes.GalleryScope
    @Named("GalleryCompositeDisposable")
    fun provideGalleryCompositeDisposable() =
            CompositeDisposable()

}
