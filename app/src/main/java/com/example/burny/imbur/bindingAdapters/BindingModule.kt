package com.example.burny.imbur.bindingAdapters

import com.example.burny.imbur.di.Scopes
import dagger.Module
import dagger.Provides

@Module
class BindingModule {

    @Provides
    @Scopes.DataBindingScope
    fun provideImageAdapter() = ImageBindingAdapter()

    @Provides
    @Scopes.DataBindingScope
    fun provideTitleAdapter() = TitleBindingAdapter()

    @Provides
    @Scopes.DataBindingScope
    fun provideListAdapter() = ListBindingAdapter()

}