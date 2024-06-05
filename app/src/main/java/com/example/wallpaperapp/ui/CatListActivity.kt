package com.example.wallpaperapp.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperapp.adapter.CatListAdapter
import com.example.wallpaperapp.databinding.ActivityCatListBinding
import com.example.wallpaperapp.model.Categories
import com.google.firebase.firestore.FirebaseFirestore

class CatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        binding = ActivityCatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()
        val uid = intent.getStringExtra("uid")
        val name = intent.getStringExtra("name")

        db.collection("categories").document(uid!!).collection("wallpaper").addSnapshotListener { value, _ ->

            val listOfCategories = arrayListOf<Categories>()
            val data = value?.toObjects(Categories::class.java)
            listOfCategories.addAll(data!!)

            binding.catListTitle.text = name.toString()
            binding.catImagesCount.text = "${listOfCategories.size} Wallpapers are available in this category"

            binding.catListRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.catListRecyclerView.adapter = CatListAdapter(this, listOfCategories)

        }

    }
}