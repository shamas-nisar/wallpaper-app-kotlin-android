package com.example.wallpaperapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperapp.adapter.BomAdapter
import com.example.wallpaperapp.adapter.CategoriesAdapter
import com.example.wallpaperapp.adapter.ColorToneAdapter
import com.example.wallpaperapp.databinding.FragmentHomeBinding
import com.example.wallpaperapp.model.Bom
import com.example.wallpaperapp.model.Categories
import com.example.wallpaperapp.model.ColorTones
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false )

        db = FirebaseFirestore.getInstance()

        db.collection("bestofmonth").addSnapshotListener { value, error ->
            val listBestOFMonth = arrayListOf<Bom>()
            val data = value?.toObjects(Bom::class.java)
            listBestOFMonth.addAll(data!!)

            binding.bestOfMonthRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.bestOfMonthRecyclerview.adapter = BomAdapter(requireContext(), listBestOFMonth)

        }

        db.collection("colortones").addSnapshotListener { value, error ->
            val listOfColorTones = arrayListOf<ColorTones>()
            val data = value?.toObjects(ColorTones::class.java)
            listOfColorTones.addAll(data!!)

            binding.theColorToneRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.theColorToneRecyclerview.adapter = ColorToneAdapter(requireContext(), listOfColorTones)

        }

        db.collection("categories").addSnapshotListener { value, error ->
            val listOfCategories = arrayListOf<Categories>()
            val data = value?.toObjects(Categories::class.java)
            listOfCategories.addAll(data!!)

            val layoutManagement = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.categoryRecyclerview.layoutManager = layoutManagement
            binding.categoryRecyclerview.adapter = CategoriesAdapter(requireContext(), listOfCategories)
//            binding.categoryRecyclerview.layoutManager = GridLayoutManager(requireContext(),2)
//            binding.categoryRecyclerview.adapter = CategoriesAdapter(requireContext(), listOfCategories)

        }

        return binding.root
    }


}