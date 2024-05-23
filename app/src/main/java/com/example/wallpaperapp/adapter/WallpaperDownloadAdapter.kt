package com.example.wallpaperapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.R
import com.makeramen.roundedimageview.RoundedImageView

class WallpaperDownloadAdapter(val requireContext: Context, val listBestOFMonth: ArrayList<String>) : RecyclerView.Adapter<WallpaperDownloadAdapter.bomViewHolder>() {

    inner class bomViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val showImage = itemView.findViewById<RoundedImageView>(R.id.catList_images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_cat_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
        Glide.with(requireContext).load(listBestOFMonth[position]).into(holder.showImage)
    }

    override fun getItemCount() = listBestOFMonth.size

}