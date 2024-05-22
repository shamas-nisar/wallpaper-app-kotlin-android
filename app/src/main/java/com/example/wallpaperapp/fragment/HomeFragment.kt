package com.example.wallpaperapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaperapp.adapter.AdapterBestOfMonth
import com.example.wallpaperapp.adapter.AdapterCategories
import com.example.wallpaperapp.adapter.AdapterColorTone
import com.example.wallpaperapp.databinding.FragmentHomeBinding
import com.example.wallpaperapp.model.ModelBestOfMonth
import com.example.wallpaperapp.model.ModelCategories
import com.example.wallpaperapp.model.ModelColorTones
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
            val listBestOFMonth = arrayListOf<ModelBestOfMonth>()
            val data = value?.toObjects(ModelBestOfMonth::class.java)
            listBestOFMonth.addAll(data!!)

            binding.bestOfMonthRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
            binding.bestOfMonthRecyclerview.adapter = AdapterBestOfMonth(requireContext(), listBestOFMonth)

        }

        db.collection("colortones").addSnapshotListener { value, error ->
            val listOfColorTones = arrayListOf<ModelColorTones>()
            val data = value?.toObjects(ModelColorTones::class.java)
            listOfColorTones.addAll(data!!)

            binding.theColorToneRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
            binding.theColorToneRecyclerview.adapter = AdapterColorTone(requireContext(), listOfColorTones)

        }

        db.collection("categories").addSnapshotListener { value, error ->
            val listOfCategories = arrayListOf<ModelCategories>()
            val data = value?.toObjects(ModelCategories::class.java)
            listOfCategories.addAll(data!!)

            binding.theColorToneRecyclerview.layoutManager = GridLayoutManager(requireContext(),2)
            binding.categoryRecyclerview.adapter = AdapterCategories(requireContext(), listOfCategories)

        }

        return binding.root
    }


}