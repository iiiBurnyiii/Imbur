package com.example.burny.imbur.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.burny.imbur.R
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageAdapter @Inject constructor(val picasso: Picasso) {

    //Заменить Picasso на Glide
    @BindingAdapter("imgUrl")
    fun setImage(iv: ImageView, url: String?) =
            picasso.load(url)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.ic_image)
                    .fit()
                    .into(iv)


}