package com.example.pjbohp.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Hape(

	@field:SerializedName("")
	val data: MutableList<HapeItem>
) : Parcelable

@Parcelize
data class HapeItem(

	@field:SerializedName("merk")
	val merk: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("harga")
	val harga: String? = null,

	@field:SerializedName("kondisi")
	val kondisi: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("tanggal_publish")
	val tanggalPublish: String? = null,

	@field:SerializedName("id")
	val id: Int? = null

) : Parcelable
