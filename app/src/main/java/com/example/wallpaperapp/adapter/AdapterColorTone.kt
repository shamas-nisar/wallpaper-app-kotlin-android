package com.example.wallpaperapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.FinalActivity
import com.example.wallpaperapp.R
import com.example.wallpaperapp.model.ModelBestOfMonth
import com.example.wallpaperapp.model.ModelColorTones

class AdapterColorTone(val requireContext: Context, val listOfColorTones: ArrayList<ModelColorTones>) : RecyclerView.Adapter<AdapterColorTone.bomViewHolder>() {

    inner class bomViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val showCard = itemView.findViewById<CardView>(R.id.color_card_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_color_tones, parent, false)
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
        val color = listOfColorTones[position].color
        holder.showCard.setCardBackgroundColor(Color.parseColor(color!!))

        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, FinalActivity::class.java)
            intent.putExtra("link", listOfColorTones[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listOfColorTones.size

}