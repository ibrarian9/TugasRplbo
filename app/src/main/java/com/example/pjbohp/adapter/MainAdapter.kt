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
import com.example.pjbohp.models.HapeItem
import com.example.pjbohp.R
import com.example.pjbohp.models.ResponseItem

class MainAdapter (private val data: MutableList<ResponseItem>): RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        val listData = data[pos]

        holder.tvNama.text = listData.nama
        holder.tvHarga.text = listData.harga
        holder.itemView.setOnClickListener{ v ->
            val intent = Intent(v.context, DetailActivity::class.java)
            intent.putExtra("nama", listData.nama)
            intent.putExtra("harga", listData.harga)
            intent.putExtra("foto", listData.foto)
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

    class MyViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val image: ImageView = v.findViewById(R.id.ivHape)
        val tvNama: TextView = v.findViewById(R.id.tvName)
        val tvHarga: TextView = v.findViewById(R.id.tvHarga)
    }
}