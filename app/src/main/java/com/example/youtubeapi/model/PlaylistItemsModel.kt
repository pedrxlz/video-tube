package com.example.youtubeapi.model

import com.google.gson.annotations.SerializedName

data class PlaylistItemsModel(
    @SerializedName("nextPageToken")
    val nextPageToken: String?,

    @SerializedName("items")
    val items: List<PlaylistYtModel.PlaylistItem>

)
