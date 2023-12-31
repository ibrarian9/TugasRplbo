package com.example.pjbohp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pjbohp.adapter.MainAdapter
import com.example.pjbohp.callApi.ApiBase
import com.example.pjbohp.models.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale
import java.util.Random

class MainActivity : AppCompatActivity() {

    lateinit var mainAdapter: MainAdapter
    private lateinit var rv: RecyclerView
    private var lm: RecyclerView.LayoutManager? = null
    var data: MutableList<ResponseItem> = ArrayList()
    private lateinit var dataKosong: TextView
    private lateinit var dataSearch: TextView
    private lateinit var stateKosong: ImageView
    private lateinit var refresh: SwipeRefreshLayout

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          Thread.sleep(1000)
          installSplashScreen()
        setContentView(R.layout.activity_main)

        val pbar: ProgressBar = findViewById(R.id.pBar)

        //  text for data kosong
        dataKosong = findViewById(R.id.dataKosong)
        dataSearch = findViewById(R.id.tvHp)
        stateKosong = findViewById(R.id.nothingState)
        refresh = findViewById(R.id.refresh)

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
                stateKosong.visibility = View.VISIBLE
                //  Menghilangkan Loading
                pbar.visibility = View.GONE
            }
        })

        refresh.setOnRefreshListener {
            refresh.isRefreshing = false
            data.shuffle(Random(System.currentTimeMillis()))
            mainAdapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun filterList(query: String?) {
        val filteredList: ArrayList<ResponseItem> = ArrayList()
        if (query != null && query != "") {
            dataSearch.text = "Menampilkan hasil untuk '$query' "
            for (i in data){
                if (i.nama?.lowercase(Locale.ROOT)?.contains(query) == true) {
                    filteredList.add(i)
                }
            }
            //  Jika data tidak ada
            if (filteredList.isEmpty()){
                rv.visibility = View.GONE
                stateKosong.visibility = View.VISIBLE
            } else {
                rv.visibility = View.VISIBLE
                stateKosong.visibility = View.GONE
                mainAdapter.filterList(filteredList)
            }
        } else {
            dataSearch.text = "Daftar Handphone"
            rv.visibility = View.VISIBLE
            stateKosong.visibility = View.GONE
            mainAdapter.setData(data)
        }
    }
}

