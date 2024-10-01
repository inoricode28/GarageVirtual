package com.example.afinal.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GarageCliente {

    private var retrofitCliente = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(15, TimeUnit.MINUTES)
        .writeTimeout(15, TimeUnit.MINUTES)
        //.addInterceptor(ApiInterceptor())
        .build()

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("http://192.168.1.171:4000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(retrofitCliente)
        .build()

    val retrofitService: GarageServicio by lazy {
        buildRetrofit().create(GarageServicio::class.java)
    }
}