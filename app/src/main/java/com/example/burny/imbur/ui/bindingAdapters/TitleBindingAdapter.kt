package com.example.burny.imbur.ui.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TitleBindingAdapter {

    @JvmStatic
    @BindingAdapter("title")
    fun TextView.setTrimTitle(title: String) {
        this.text = when (title.length <= 60) {
            true -> title
            false -> title.take(57) + "..."
        }
    }

}