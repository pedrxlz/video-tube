package com.example.youtubeapi.network

import com.example.youtubeapi.model.ChannelModel
import com.example.youtubeapi.model.PlaylistItemsModel
import com.example.youtubeapi.model.VideoYtModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("channels")
    fun getChannel(
        @Query("part") part: String,
        @Query("id") id: String
    ) : Call<ChannelModel>

    @GET("playlistItems")
    fun getPlaylist(
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: String
    ) : Call<VideoYtModel>

}