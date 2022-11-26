package com.example.youtubeapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapi.databinding.ItemVideoBinding
import com.example.youtubeapi.diffutils.VideoDiffUtil
import com.example.youtubeapi.model.VideoYtModel

class VideoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */

    private var oldItems = emptyList<VideoYtModel.VideoItem>()

    class VideoHolder(itemView: ItemVideoBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun setData(data: VideoYtModel.VideoItem) {
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

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VideoHolder).setData(oldItems[position])
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return oldItems.size
    }

    fun setData(newList: List<VideoYtModel.VideoItem>){
        val videoDiff = VideoDiffUtil(oldItems, newList)
        val diff = DiffUtil.calculateDiff(videoDiff)
        oldItems = newList
        diff.dispatchUpdatesTo(this)

    }

}