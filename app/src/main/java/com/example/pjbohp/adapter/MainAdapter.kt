package com.example.pjbohp.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pjbohp.DetailActivity
import com.example.pjbohp.R
import com.example.pjbohp.callApi.ApiBase
import com.example.pjbohp.models.ResponseItem
import com.squareup.picasso.Picasso

class MainAdapter (private var data: MutableList<ResponseItem>): RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private val urlBase = ApiBase().urlBase + "poto/"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        val listData = data[pos]
        val potoData = urlBase + listData.id

        holder.tvNama.text = listData.nama
        holder.tvHarga.text = "Rp" + listData.harga
        if (listData.foto.equals("")){
            holder.image.setImageResource(R.drawable.noimage)
        } else {
            Picasso.get().load(potoData).into(holder.image)
        }
        holder.itemView.setOnClickListener{ v ->
            val intent = Intent(v.context, DetailActivity::class.java)
            intent.putExtra("nama", listData.nama)
            intent.putExtra("harga", listData.harga)
            intent.putExtra("id", listData.id)
            intent.putExtra("poto", listData.foto)
            intent.putExtra("desc", listData.desc)
            v.context.startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataa: List<ResponseItem>) {
        data.apply {
            clear()
            addAll(dataa)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteList: MutableList<ResponseItem>) {
        this.data = filteList
        notifyDataSetChanged()
    }

    class MyViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val image: ImageView = v.findViewById(R.id.ivHape)
        val tvNama: TextView = v.findViewById(R.id.tvName)
        val tvHarga: TextView = v.findViewById(R.id.tvHarga)
    }
}