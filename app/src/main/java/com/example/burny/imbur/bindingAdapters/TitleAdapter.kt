package com.example.burny.imbur.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

class TitleAdapter {

    @BindingAdapter("title")
    fun setTrimTitle(tv: TextView, title: String) {
        tv.text = when (title.length <= 70) {
            true -> title
            false -> title.take(67) + "..."
        }
    }

}