package com.example.pjbohp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Nama
        val dataName = intent.getStringExtra("nama")
        val nama: TextView = findViewById(R.id.tvName)
        nama.text = dataName

        // Harga
        val dataHarga = intent.getStringExtra("harga")
        val harga: TextView = findViewById(R.id.tvHarga)
        harga.text = dataHarga

        // Back
        val back: FloatingActionButton = findViewById(R.id.backBtn)
        back.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

    }
}