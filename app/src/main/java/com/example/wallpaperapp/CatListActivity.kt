package com.example.wallpaperapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperapp.adapter.AdapterCatList
import com.example.wallpaperapp.databinding.ActivityCatListBinding
import com.example.wallpaperapp.model.ModelCategories
import com.google.firebase.firestore.FirebaseFirestore

class CatListActivity : AppCompatActivity() {

    lateinit var binding: ActivityCatListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()
        val uid = intent.getStringExtra("uid")
        val name = intent.getStringExtra("name")

        db.collection("categories").addSnapshotListener { value, error ->
            val listOfCategories = arrayListOf<ModelCategories>()
            val data = value?.toObjects(ModelCategories::class.java)
            listOfCategories.addAll(data!!)

            binding.catListTitle.text = name.toString()
            binding.catImagesCount.text = "${listOfCategories.size} Wallpapers are available in this category"

            binding.catListRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.catListRecyclerView.adapter = AdapterCatList(this, listOfCategories)

        }

    }
}