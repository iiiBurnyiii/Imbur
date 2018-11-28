package com.example.burny.imbur.bindingAdapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.burny.imbur.utils.GlideApp

class ImageBindingAdapter {

    @BindingAdapter("imgUrl", "error")
    fun ImageView.setImage(url: String, errorDrawable: Drawable) {

        val circularProgressDrawable = CircularProgressDrawable(this.context)
        with(circularProgressDrawable) {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        GlideApp.with(context).load(url)
                .placeholder(circularProgressDrawable)
                .error(errorDrawable)
                .into(this)
    }

}