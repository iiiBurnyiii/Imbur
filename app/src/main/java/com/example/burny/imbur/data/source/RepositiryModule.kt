package com.example.burny.imbur.data.source

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositiryModule {

    @Binds
    @Singleton
    abstract fun bindGalleryRepository(repository: GalleryRepository): DataSource

}