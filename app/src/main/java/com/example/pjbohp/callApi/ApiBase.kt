package com.example.pjbohp.callApi

import com.example.pjbohp.models.ResponseItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiBase {

    private val url = "192.168.1.10:8080"
    val urlBase = "http://$url/api/hape/"

    private fun getRetrofit() : Retrofit {

        return Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): Data = getRetrofit().create(Data::class.java)
}

interface Data {
    @GET("tampil")
    fun getData(): Call<MutableList<ResponseItem>>
}