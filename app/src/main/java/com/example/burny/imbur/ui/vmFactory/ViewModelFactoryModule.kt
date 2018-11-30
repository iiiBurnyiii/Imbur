package com.example.burny.imbur.ui.vmFactory

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}

