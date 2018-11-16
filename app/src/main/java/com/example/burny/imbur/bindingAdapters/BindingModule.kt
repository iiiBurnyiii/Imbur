package com.example.burny.imbur.bindingAdapters

import com.example.burny.imbur.di.Scopes
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class BindingModule {

    @Provides
    @Scopes.DataBindingScope
    fun provideImageAdapter(picasso: Picasso) = ImageAdapter(picasso)

    @Provides
    @Scopes.DataBindingScope
    fun provideTitleAdapter() = TitleAdapter()

}