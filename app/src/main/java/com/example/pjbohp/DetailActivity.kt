package com.example.pjbohp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.pjbohp.callApi.ApiBase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
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
        harga.text = "Rp$dataHarga"

        //  Desc
        val dataDesc = intent.getStringExtra("desc")
        val desc: TextView = findViewById(R.id.tvDesc)
        desc.text = dataDesc

        // Poto
        val url = ApiBase().urlBase + "poto/"
        val dataImage = intent.getIntExtra("id", 1)
        val potoo = intent.getStringExtra("poto")
        val poto: ImageView = findViewById(R.id.imageView)
        if (potoo.equals("")){
            poto.setImageResource(R.drawable.noimage)
        } else {
            Picasso.get().load(url + dataImage).into(poto)
        }

        // Back Button
        val back: FloatingActionButton = findViewById(R.id.backBtn)
        back.setOnClickListener { onBackPressed() }
    }
}