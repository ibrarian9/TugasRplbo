package com.example.pjbohp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.pjbohp.callApi.ApiBase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

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

        // Poto
        val url = ApiBase().urlBase + "poto/"
        val dataImage = intent.getIntExtra("id", 1)
        val poto: ImageView = findViewById(R.id.imageView)
        println(url + dataImage)
        Picasso.get().load(url + dataImage).into(poto)

        // Back
        val back: FloatingActionButton = findViewById(R.id.backBtn)
        back.setOnClickListener {startActivity(Intent(this, MainActivity::class.java))}
    }
}