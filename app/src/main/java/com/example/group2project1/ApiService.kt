package com.example.group2project1

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("planetary/apod")
    suspend fun getAPOD(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): APODModel
}