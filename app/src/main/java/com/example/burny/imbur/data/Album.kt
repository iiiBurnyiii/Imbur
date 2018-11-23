package com.example.burny.imbur.data


data class Album(
        val id: String,
        val author: String,
        val title: String,
        val cover: String,
        val datetime: Long,
        val coverWidth: Int,
        val coverHeight: Int,
        val views: Int,
        val link: String,
        val ups: Int,
        val downs: Int,
        val points: Int,
        val score: Int,
        val is_album: Boolean,
        val favoriteCount: Int,
        val commentCount: Int,
        val comments: ArrayList<Comment>,
        val images: ArrayList<Image>
) {

    fun getCoverRatio() = "$coverWidth:$coverHeight"

    fun getCoverUrl(): String {
        return if (is_album) {
            images[0].link
        } else {
            link
        }
    }
}