package com.example.pjbohp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pjbohp.callApi.ApiBase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var btnWa: RelativeLayout

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

        btnWa = findViewById(R.id.btnWa)
        btnWa.setOnClickListener{
            val contact = "+6281371006419"
            val message = "Spesifikasi Barang " +
                    "%0aNama Barang : " + dataName +
                    "%0aDesc : " + dataDesc +
                    "%0aHarga : " + dataHarga

            val urii = "https://api.whatsapp.com/send?phone=$contact&text=$message"

            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(urii))
            startActivity(i)
        }

        // Back Button
        val back: FloatingActionButton = findViewById(R.id.backBtn)
        back.setOnClickListener { onBackPressed() }
    }
}