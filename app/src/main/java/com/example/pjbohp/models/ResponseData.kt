package com.example.pjbohp.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseItem (

	@field:SerializedName("merk")
	val merk: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("harga")
	val harga: String? = null,

	@field:SerializedName("deskripsi")
	val desc: String? = null,

	@field:SerializedName("kondisi")
	val kondisi: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("imagePath")
	val imagePath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null

) : Parcelable
