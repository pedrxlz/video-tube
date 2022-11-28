package com.example.youtubeapi.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ThumbnailsYt(
    @SerializedName("default")
    val high: High
) {
    data class High(
        @SerializedName("url")
        val url: String
    )


}