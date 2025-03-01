package com.example.group2project1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NasaRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiService::class.java)

    suspend fun getAPOD(date: String): APODModel {
        return service.getAPOD("48Q2w8fEsnd6CDdTV2dkzy9iFeuQRwQQARS5IjcR", date)
    }
}