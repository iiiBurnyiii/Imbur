package com.example.burny.imbur.data

import android.media.Image

data class Album(
        val id: String,
        val author: String = "",
        val title: String,
        val coverId: String, //url, need replace
        val datetime: Long = 0,
        val coverWidth: Int,
        val coverHeight: Int,
        val views: Int = 0,
        val link: String = "",
        val ups: Int = 0,
        val downs: Int = 0,
        val points: Int,
        val favoriteCount: Int = 0,
        val commentCount: Int = 0,
        val comments: ArrayList<Comment> = ArrayList(),
        val images: ArrayList<Image> = ArrayList()
) {

    val coverRatio = "$coverWidth:$coverHeight"

}