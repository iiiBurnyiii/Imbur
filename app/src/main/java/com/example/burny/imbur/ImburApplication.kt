package com.example.burny.imbur

import com.example.burny.imbur.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ImburApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val appComponent = DaggerAppComponent.create()
        appComponent.inject(this)

        return appComponent
    }

}