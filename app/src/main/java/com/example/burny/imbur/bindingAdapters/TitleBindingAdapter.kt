package com.example.burny.imbur.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

class TitleBindingAdapter {

    @BindingAdapter("title")
    fun TextView.setTrimTitle(title: String) {
        this.text = when (title.length <= 60) {
            true -> title
            false -> title.take(57) + "..."
        }
    }

}