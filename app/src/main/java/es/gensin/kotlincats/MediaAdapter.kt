package es.gensin.kotlincats

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_media_item.view.*
import kotlin.properties.Delegates

class MediaAdapter(data: List<MediaItem> = emptyList(), val onItemClick: (MediaItem) -> Unit): RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var data: List<MediaItem> by Delegates.observable(data) { prop, old, new ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = parent.inflate(R.layout.view_media_item)
        return ViewHolder(v, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size

    class ViewHolder(view: View, val onItemClick: (MediaItem) -> Unit): RecyclerView.ViewHolder(view) {

        fun bind(mediaItem: MediaItem) = with(itemView) {
            media_title.text = mediaItem.title
            media_thumb.loadUrl(mediaItem.url)
            media_video_indicator.visibility = when (mediaItem.type) {
                MediaItem.Type.PHOTO -> View.GONE
                MediaItem.Type.VIDEO -> View.VISIBLE
            }
            setOnClickListener { onItemClick(mediaItem) }
        }
    }
}
