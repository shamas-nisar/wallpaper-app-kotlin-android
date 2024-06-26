package com.example.wallpaperapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.ui.FinalActivity
import com.example.wallpaperapp.R
import com.example.wallpaperapp.model.Categories
import com.makeramen.roundedimageview.RoundedImageView

class CatListAdapter(private val requireContext: Context, private val listBestOFMonth: ArrayList<Categories>) : RecyclerView.Adapter<CatListAdapter.CatListViewHolder>() {

    inner class CatListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val showImage: RoundedImageView = itemView.findViewById(R.id.catList_images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListViewHolder {
        return CatListViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_cat_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CatListViewHolder, position: Int) {
        Glide.with(requireContext).load(listBestOFMonth[position].link).into(holder.showImage)
        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, FinalActivity::class.java)
            intent.putExtra("link", listBestOFMonth[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listBestOFMonth.size

}