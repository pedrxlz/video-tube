package com.example.youtubeapi.adapter

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapi.activities.PlayerActivity
import com.example.youtubeapi.databinding.ItemVideoBinding
import com.example.youtubeapi.diffutils.VideoDiffUtil
import com.example.youtubeapi.model.FirebaseVideoYtModel
import com.example.youtubeapi.model.VideoYtModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class VideoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var oldItems = emptyList<VideoYtModel.VideoItem>()


    class VideoHolder(itemView: ItemVideoBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        private lateinit var database: DatabaseReference
        private lateinit var firebaseAuth: FirebaseAuth

        fun setData(data: VideoYtModel.VideoItem) {

            firebaseAuth = FirebaseAuth.getInstance()
            binding.likeButton.setOnClickListener {
                database = FirebaseDatabase.getInstance().getReference(firebaseAuth.uid.toString())
                val video = FirebaseVideoYtModel(
                    data.snippetYt.resourceId.videoId,
                    data.snippetYt.title,
                    data.snippetYt.description,
                    data.snippetYt.publishedAt,
                    data.snippetYt.thumbnails
                )
                database.child(data.snippetYt.resourceId.videoId).setValue(video).addOnSuccessListener {
                    val colorValue = Color.parseColor("#0099FF")
                    ImageViewCompat.setImageTintList(
                        binding.likeButton,
                        ColorStateList.valueOf(colorValue)
                    );
                }.addOnFailureListener {
                    Snackbar.make(binding.root, "Falha ao curtir o vídeo", Snackbar.LENGTH_SHORT)
                        .show()
                }

            }

            binding.root.setOnClickListener {
                val i = Intent(it.context, PlayerActivity::class.java)
                i.putExtra("video_id", data.snippetYt.resourceId.videoId)
                i.putExtra("video_title", data.snippetYt.title)
                i.putExtra("video_description", data.snippetYt.description)
                it.context.startActivity(i)
            }

            binding.tvVideoTitle.text = data.snippetYt.title
            binding.tvPublished.text = data.snippetYt.publishedAt
            Glide.with(binding.root)
                .load(data.snippetYt.thumbnails.high.url)
                .into(binding.ivThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VideoHolder).setData(oldItems[position])
    }


    override fun getItemCount(): Int {
        return oldItems.size
    }

    fun setData(newList: List<VideoYtModel.VideoItem>) {
        val videoDiff = VideoDiffUtil(oldItems, newList)
        val diff = DiffUtil.calculateDiff(videoDiff)
        oldItems = newList
        diff.dispatchUpdatesTo(this)
    }

}