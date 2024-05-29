package com.example.wallpaperapp.ui

import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wallpaperapp.databinding.ActivityFinalBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.util.Objects
import kotlin.random.Random

class FinalActivity : AppCompatActivity() {

    lateinit var binding: ActivityFinalBinding
    var urlImage : URL? = null
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("link")

        try {
            urlImage = URL(url)
        } catch (e: Exception) {
            e.stackTrace
        }
        Glide.with(this).load(url).into(binding.finalActivityWallpaper)

        // code for set wallpaper -------------->
        binding.buttonSetWallpaper.setOnClickListener{
            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage?.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main) {

                val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                wallpaperManager.setBitmap(result.await())
                Toast.makeText(
                    this@FinalActivity,
                    "Wallpaper set successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        // code for download wallpaper --------->
        binding.buttonDownload.setOnClickListener{

            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage?.toBitmap()
            }

            GlobalScope.launch(Dispatchers.Main) {
                saveImage(result.await())
            }
        }
    }

    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e:IOException) {
            null
        }
    }

    private fun saveImage(image: Bitmap?) {
        val random1 = Random.nextInt(520985)
        val random2 =Random.nextInt(952663)

        val name = "Amoled-${random1 + random2}"
        val data: OutputStream

        try {
            val resolver = contentResolver
            val contentValues = ContentValues()

            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + "WallpaperApp"
            )

            val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            data = resolver.openOutputStream(Objects.requireNonNull(imageUri)!!)!!
            image?.compress(Bitmap.CompressFormat.JPEG, 100, data)
            Objects.requireNonNull<OutputStream?>(data)
            Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Image not saved", Toast.LENGTH_SHORT).show()
        }
    }
}