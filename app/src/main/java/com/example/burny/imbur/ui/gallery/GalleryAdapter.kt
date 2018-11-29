package com.example.burny.imbur.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.burny.imbur.data.to.Album
import com.example.burny.imbur.databinding.GalleryItemBinding
import javax.inject.Inject

class GalleryAdapter @Inject constructor ():
        PagedListAdapter<Album, GalleryAdapter.GalleryViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryItemBinding.inflate(inflater, parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
                    oldItem == newItem

        }
    }

    inner class GalleryViewHolder(private val binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album?) {
            binding.album = album
            binding.executePendingBindings()
        }

    }

}