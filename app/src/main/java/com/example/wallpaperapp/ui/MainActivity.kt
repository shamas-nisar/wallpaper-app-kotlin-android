package com.example.wallpaperapp.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.ActivityMainBinding
import com.example.wallpaperapp.fragment.DownloadFragment
import com.example.wallpaperapp.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // To change the interface when the user clicks on the fragment
        replaceFragment(HomeFragment())

        // to show the home fragment screen when user clicks on the home button.
        binding.icHome.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        // to show the download fragment screen when user clicks on the download button.
        binding.icDownload.setOnClickListener {
            replaceFragment(DownloadFragment())
        }

    }

    // changing the interface when user clicks on either Home or Download button.
    // And based on that the ui should change
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentReplace, fragment)
        transaction.commit()
    }
}