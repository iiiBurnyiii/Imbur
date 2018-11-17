package com.example.burny.imbur

import androidx.databinding.DataBindingUtil
import com.example.burny.imbur.bindingAdapters.DaggerBindingComponent
import com.example.burny.imbur.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ImburApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val appComponent = DaggerAppComponent.create()
        appComponent.inject(this)

        val bindingComponent = DaggerBindingComponent.builder()
                .appComponent(appComponent)
                .build()
        DataBindingUtil.setDefaultComponent(bindingComponent)

        return appComponent
    }

}