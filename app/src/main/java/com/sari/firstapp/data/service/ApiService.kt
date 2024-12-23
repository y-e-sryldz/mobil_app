package com.sari.firstapp.data.service

import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<String>
}
