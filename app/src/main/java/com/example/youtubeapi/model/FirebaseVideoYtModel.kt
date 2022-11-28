package com.example.youtubeapi.model

data class FirebaseVideoYtModel(
    val id: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val thumbnails: ThumbnailsYt
) {
    data class Thumbnails(
        val default: Default
    )

    class Default(
        val url: String
    )


}