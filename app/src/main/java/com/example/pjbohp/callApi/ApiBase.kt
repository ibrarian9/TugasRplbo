package com.example.pjbohp.callApi

import com.example.pjbohp.models.ResponseItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiBase {

    private fun getRetrofit() : Retrofit {

        val url = "192.168.1.9:8080"
        val urlBase = "http://$url/api/hape/"

        return Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): Data = getRetrofit().create(Data::class.java)
}

interface Data {
    @GET("all")
    fun getData(): Call<MutableList<ResponseItem>>
}