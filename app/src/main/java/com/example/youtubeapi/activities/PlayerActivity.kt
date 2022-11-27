package com.example.youtubeapi.activities

import android.os.Bundle
import android.util.Log
import com.example.youtubeapi.BuildConfig

import com.example.youtubeapi.databinding.ActivityPlayerBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer


class PlayerActivity : YouTubeBaseActivity() {

    private var _binding: ActivityPlayerBinding? = null
    private val binding get() = _binding!!
    private var youtubePlayer: YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoId = intent.getStringExtra("video_id")
        val videoTitle = intent.getStringExtra("video_title")
        val videoDescription = intent.getStringExtra("video_description")

        binding.tvVideoTitle.text = videoTitle
        binding.tvVideoDescription.text = videoDescription

        binding.youtubePlayer.initialize(BuildConfig.YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                youtubePlayer = p1
                youtubePlayer?.loadVideo(videoId)
                youtubePlayer?.play()

            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                if (videoId != null) {
                    Snackbar.make(binding.root, videoId, Snackbar.LENGTH_SHORT).show()

                }
            }
        })



    }

}