package com.example.wallpaperapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.FinalActivity
import com.example.wallpaperapp.R
import com.example.wallpaperapp.model.ModelCategories
import com.makeramen.roundedimageview.RoundedImageView

class AdapterCatList(val requireContext: Context, val listBestOFMonth: ArrayList<ModelCategories>) : RecyclerView.Adapter<AdapterCatList.bomViewHolder>() {

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
        Glide.with(requireContext).load(listBestOFMonth[position].link).into(holder.showImage)
        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, FinalActivity::class.java)
            intent.putExtra("link", listBestOFMonth[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listBestOFMonth.size

}