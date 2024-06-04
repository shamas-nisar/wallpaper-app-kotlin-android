package com.example.wallpaperapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.ui.FinalActivity
import com.example.wallpaperapp.R
import com.example.wallpaperapp.model.Bom

class BomAdapter(private val requireContext: Context, private val listBestOFMonth: ArrayList<Bom>) : RecyclerView.Adapter<BomAdapter.BomViewHolder>() {

    inner class BomViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val showImage: ImageView = itemView.findViewById(R.id.home_screen_frame_1_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BomViewHolder {
        return BomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_best_of_month, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BomViewHolder, position: Int) {
        Glide.with(requireContext).load(listBestOFMonth[position].link).into(holder.showImage)
        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, FinalActivity::class.java)
            intent.putExtra("link", listBestOFMonth[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listBestOFMonth.size

}