package com.example.burny.imbur.data


data class Album(
        val id: String,
        val author: String,
        val title: String,
        val cover: String,
        val datetime: Long,
        val cover_width: Int,
        val width: Int,
        val cover_height: Int,
        val height: Int,
        val views: Int,
        val link: String,
        val ups: Int,
        val downs: Int,
        val points: Int,
        val score: Int,
        val is_album: Boolean,
        val favorite_count: Int,
        val comment_count: Int,
        val comments: ArrayList<Comment>,
        val images: ArrayList<Image>
) {

    fun getCoverDimensionRatio() =
            if (is_album) "$cover_width:$cover_height" else "$width:$height"

    fun getCoverUrl() =
            if (is_album) images[0].link else link
}