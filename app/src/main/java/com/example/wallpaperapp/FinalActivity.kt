package com.example.wallpaperapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.wallpaperapp.databinding.ActivityFinalBinding

class FinalActivity : AppCompatActivity() {
    lateinit var binding: ActivityFinalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("link")
        Glide.with(this).load(url).into(binding.finalActivityWallpaper)


        binding.buttonDownload.setOnClickListener{
            // code for download wallpaper
        }

        binding.buttonSetWallpaper.setOnClickListener{
            // code for set wallpaper
        }

    }
}