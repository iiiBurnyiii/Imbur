package com.example.burny.imbur.ui

import android.os.Bundle
import com.example.burny.imbur.R
import com.example.burny.imbur.ui.gallery.GalleryFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, GalleryFragment.newInstance())
                    .commitNow()
        }
    }

}
