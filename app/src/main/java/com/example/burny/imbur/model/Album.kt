package com.example.burny.imbur.model

import com.google.gson.annotations.SerializedName


data class Album (
        val id: String,
        val author: String,
        val title: String,
        val cover: String,
        val datetime: Long,
        @SerializedName("cover_width", alternate = ["width"])
        val width: Int,
        @SerializedName("cover_height", alternate = ["height"])
        val height: Int,
        val views: Int,
        val link: String,
        val ups: Int,
        val downs: Int,
        val points: Int,
        val score: Int,
        @SerializedName("is_album")
        val isAlbum: Boolean,
        @SerializedName("favorite_count")
        val favoriteCount: Int,
        @SerializedName("comment_count")
        val commentCount: Int,
        val images: ArrayList<Image>
) {

    fun getCoverDimensionRatio() =
            "$width:${if (height >= 1080) 1080 else height}"

    fun getCoverUrl() =
            if (isAlbum) images[0].link else link

}