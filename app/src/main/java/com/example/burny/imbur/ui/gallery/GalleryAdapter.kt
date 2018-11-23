package com.example.burny.imbur.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.burny.imbur.data.Album
import com.example.burny.imbur.databinding.GalleryItemBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var items = ArrayList<Album>()

    fun replaceData(data: ArrayList<Album>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryItemBinding.inflate(inflater, parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int =
            items.size

    inner class GalleryViewHolder(private val binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            binding.album = album
            binding.executePendingBindings()
        }

    }

}