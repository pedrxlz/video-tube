package com.example.youtubeapi.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SnippetYt(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,


    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("thumbnails")
    val thumbnails: ThumbnailsYt,

    @SerializedName("position")
    val country: Int?,

    @SerializedName("resourceId")
    val resourceId: VideoId



) {
    data class VideoId (
        @SerializedName("videoId")
        val videoId: String
        )
}