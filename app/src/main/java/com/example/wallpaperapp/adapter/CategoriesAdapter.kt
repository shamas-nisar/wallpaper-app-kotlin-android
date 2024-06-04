package com.example.wallpaperapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.ui.CatListActivity
import com.example.wallpaperapp.R
import com.example.wallpaperapp.model.Categories

class CategoriesAdapter(private val requireContext: Context, private val listOfCategories: ArrayList<Categories>) : RecyclerView.Adapter<CategoriesAdapter.CatViewHolder>() {

    inner class CatViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val showImage: ImageView = itemView.findViewById(R.id.cat_img)
        val catName: TextView = itemView.findViewById(R.id.cat_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_categories, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.catName.text = listOfCategories[position].name
        Glide.with(requireContext).load(listOfCategories[position].link).into(holder.showImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, CatListActivity::class.java)
            intent.putExtra("uid", listOfCategories[position].id)
            intent.putExtra("name", listOfCategories[position].name)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listOfCategories.size

}