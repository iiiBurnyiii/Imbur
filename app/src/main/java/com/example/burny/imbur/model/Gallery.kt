package com.example.burny.imbur.model

import com.google.gson.annotations.SerializedName

data class Gallery (
        @SerializedName("data") val data: ArrayList<Album>,
        val nextPage: Int
)