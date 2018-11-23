package com.example.burny.imbur.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.burny.imbur.R
import com.example.burny.imbur.data.Album
import com.example.burny.imbur.databinding.GalleryItemBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var items = ArrayList<Album>()
    private val set = ConstraintSet()

    fun replaceData(data: ArrayList<Album>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<GalleryItemBinding>(inflater, R.layout.gallery_item, parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = items[position]

//        with(set){
//            clone(holder.layout)
//            setDimensionRatio(holder.img.id, item.getCoverRatio())
//            applyTo(holder.layout)
//        }

        holder.bind(item)
    }

    override fun getItemCount(): Int =
            items.size


    inner class GalleryViewHolder(private val binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val layout = binding.coverLayout
        val img = binding.imgSource

        fun bind(album: Album) {
            binding.album = album
            binding.executePendingBindings()
        }

    }

}