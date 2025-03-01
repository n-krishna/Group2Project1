package com.example.group2project1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigateButton = findViewById<Button>(R.id.btn_navigate)

        navigateButton.setOnClickListener {
            val intent = Intent(this, NasaApodActivity::class.java)
            startActivity(intent)
        }
    }
}