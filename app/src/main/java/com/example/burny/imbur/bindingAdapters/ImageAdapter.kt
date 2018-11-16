package com.example.burny.imbur.bindingAdapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageAdapter @Inject constructor(val picasso: Picasso) {

    @BindingAdapter("imgUrl", "img_onError")
    fun setImage(iv: ImageView, url: String?, errorImg: Drawable) =
            if (url != null) {
                picasso.load(url).error(errorImg).into(iv)
            } else {
                iv.setImageDrawable(errorImg)
            }


}