package com.example.sehun.data.remote

import com.example.sehun.data.remote.api.SoptService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitClient {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val soptService: SoptService = retrofit.create(SoptService::class.java)
}
