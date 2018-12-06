package com.example.burny.imbur.utils

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String, timeLength: Int) =
        Snackbar.make(this, message, timeLength).show()

fun View.setupSnackbar(lifecycleOwner: LifecycleOwner,
                       messageLiveEvent: MutableLiveData<Int>, timeLength: Int) {

    messageLiveEvent.observe(lifecycleOwner, Observer { message ->
        message?.let {
            showSnackbar(context.getString(message), timeLength)
        }
    })

}