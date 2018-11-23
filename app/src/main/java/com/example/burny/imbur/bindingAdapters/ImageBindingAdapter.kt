package com.example.burny.imbur.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.burny.imbur.R
import com.example.burny.imbur.utils.GlideApp

class ImageBindingAdapter {

    @BindingAdapter("imgUrl")
    fun ImageView.setImage(url: String) {
        GlideApp.with(context).load(url)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image)
                .into(this)
    }

}