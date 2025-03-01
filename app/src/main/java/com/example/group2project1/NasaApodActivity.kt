package com.example.group2project1

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class NasaApodActivity : AppCompatActivity() {
    private val viewModel: NasaViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa_apod)

        val dateInput = findViewById<EditText>(R.id.dateInput)
        val fetchButton = findViewById<Button>(R.id.fetchButton)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val explanation = findViewById<TextView>(R.id.description)

        fetchButton.setOnClickListener {
            val date = dateInput.text.toString()
            if (date.isNotEmpty()) {
                viewModel.fetchAPOD(date)
            } else {
                Toast.makeText(this, "Please enter a date", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.apodData.observe(this) { apod ->
            apod?.let {
                if (apod.media_type == "image") {
                    imageView.visibility = ImageView.VISIBLE
                    Glide.with(this).load(apod.url).into(imageView)
                } else if (apod.media_type == "video") {
                    imageView.visibility = ImageView.GONE

                    // Launch YouTube video
                    val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(apod.url))
                    youtubeIntent.putExtra(Intent.EXTRA_REFERRER, Uri.parse("android-app://com.google.android.youtube"))
                    startActivity(youtubeIntent)
                }
                if (apod.explanation !=""){
                    explanation.text = apod.explanation
                }
            }
        }

        viewModel.errorMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}