package com.example.burny.imbur.ui.bindingAdapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.burny.imbur.utils.GlideApp

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imgUrl", "error")
    fun ImageView.setImage(url: String, errorDrawable: Drawable) {

        val circularProgressDrawable = CircularProgressDrawable(this.context)
        with(circularProgressDrawable) {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        GlideApp.with(context).load(url)
                .error(errorDrawable)
                .placeholder(circularProgressDrawable)
                .centerCrop()
                .into(this)
    }

}