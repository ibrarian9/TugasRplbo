package com.example.pjbohp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pjbohp.adapter.MainAdapter
import com.example.pjbohp.callApi.ApiBase
import com.example.pjbohp.models.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var mainAdapter: MainAdapter
    private lateinit var rv: RecyclerView
    private var lm: RecyclerView.LayoutManager? = null
    var data: MutableList<ResponseItem> = ArrayList()
    private lateinit var dataKosong: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          Thread.sleep(1000)
          installSplashScreen()
        setContentView(R.layout.activity_main)

        val pbar: ProgressBar = findViewById(R.id.pBar)

        //  text for data kosong
        dataKosong = findViewById(R.id.dataKosong)

        //  Fitur Cari
        val cari: SearchView = findViewById(R.id.search)
        cari.clearFocus()
        cari.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        // Memunculkan Loading
        pbar.visibility = View.VISIBLE

        // Recycler View
        rv = findViewById(R.id.rv)
        rv.setHasFixedSize(true)
        mainAdapter = MainAdapter(data)
        lm = GridLayoutManager(this, 2)
        rv.layoutManager = lm
        rv.adapter = mainAdapter

        // Memunculkan Recycler View
        rv.visibility = View.VISIBLE

        ApiBase().getService().getData().enqueue(object : Callback<MutableList<ResponseItem>> {
            override fun onResponse(call: Call<MutableList<ResponseItem>>, response: Response<MutableList<ResponseItem>>) {
                data = response.body()!!
                mainAdapter.setData(data)
                //  Menghilangkan Loading
                pbar.visibility = View.GONE
            }
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<MutableList<ResponseItem>>, t: Throwable) {
                dataKosong.visibility = View.VISIBLE
                dataKosong.text = "Data Tidak Termuat"
                //  Menghilangkan Loading
                pbar.visibility = View.GONE
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList: ArrayList<ResponseItem> = ArrayList()
            for (i in data){
                if (i.nama?.lowercase(Locale.ROOT)?.contains(query) == true){
                    filteredList.add(i)
                }
            }
            //  Jika data tidak ada
            if (filteredList.isEmpty()){
                rv.visibility = View.GONE
                dataKosong.visibility = View.VISIBLE
                dataKosong.text = "Data Tidak Ada"
            } else {
                rv.visibility = View.VISIBLE
                dataKosong.visibility = View.GONE
                mainAdapter.filterList(filteredList)
            }
        }
    }
}

