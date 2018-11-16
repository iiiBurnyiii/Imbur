package com.example.burny.imbur

import android.app.Activity
import android.app.Application
import androidx.databinding.DataBindingUtil
import com.example.burny.imbur.bindingAdapters.DaggerBindingComponent
import com.example.burny.imbur.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ImburApplication : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        val appComponent = DaggerAppComponent.create()
        appComponent.inject(this)

        val bindingComponent = DaggerBindingComponent.builder()
                .appComponent(appComponent)
                .build()
        DataBindingUtil.setDefaultComponent(bindingComponent)

    }

    override fun activityInjector(): AndroidInjector<Activity> =
            dispatchingAndroidInjector

}