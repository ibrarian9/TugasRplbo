package com.example.pjbohp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pjbohp.adapter.MainAdapter
import com.example.pjbohp.callApi.ApiBase
import com.example.pjbohp.models.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mainAdapter: MainAdapter
    private lateinit var rv: RecyclerView
    private var lm: RecyclerView.LayoutManager? = null
    var data: MutableList<ResponseItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pbar: ProgressBar = findViewById(R.id.pBar)
        val dataKosong: TextView = findViewById(R.id.dataKosong)

        pbar.visibility = View.VISIBLE

        // Recycler View
        rv = findViewById(R.id.rv)
        rv.setHasFixedSize(true)
        mainAdapter = MainAdapter(data)
        lm = GridLayoutManager(this, 2)
        rv.layoutManager = lm
        rv.adapter = mainAdapter

        ApiBase().getService().getData().enqueue(object : Callback<MutableList<ResponseItem>> {
            override fun onResponse(call: Call<MutableList<ResponseItem>>, response: Response<MutableList<ResponseItem>>) {
                data = response.body()!!
                mainAdapter.setData(data)
                pbar.visibility = View.GONE
            }
            override fun onFailure(call: Call<MutableList<ResponseItem>>, t: Throwable) {
                dataKosong.visibility = View.VISIBLE
                dataKosong.text = "Data Tidak Termuat"
                pbar.visibility = View.GONE
            }
        })
    }
}

