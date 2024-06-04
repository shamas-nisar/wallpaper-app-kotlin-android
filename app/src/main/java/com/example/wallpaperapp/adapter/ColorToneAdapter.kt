package com.example.wallpaperapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.ui.FinalActivity
import com.example.wallpaperapp.R
import com.example.wallpaperapp.model.ColorTones

@Suppress("UNREACHABLE_CODE")
class ColorToneAdapter(private val requireContext: Context, private val listOfColorTones: ArrayList<ColorTones>) : RecyclerView.Adapter<ColorToneAdapter.ColorViewHolder>(),
    Parcelable {

    inner class ColorViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val showCard: CardView = itemView.findViewById(R.id.color_card_id)
    }

    constructor(parcel: Parcel) : this(
        TODO("requireContext"),
        TODO("listOfColorTones")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_color_tones, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = listOfColorTones[position].color
        holder.showCard.setCardBackgroundColor(Color.parseColor(color))

        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, FinalActivity::class.java)
            intent.putExtra("link", listOfColorTones[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listOfColorTones.size
    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ColorToneAdapter> {
        override fun createFromParcel(parcel: Parcel): ColorToneAdapter {
            return ColorToneAdapter(parcel)
        }

        override fun newArray(size: Int): Array<ColorToneAdapter?> {
            return arrayOfNulls(size)
        }
    }

}