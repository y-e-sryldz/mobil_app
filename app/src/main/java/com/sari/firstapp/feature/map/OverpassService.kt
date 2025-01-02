package com.sari.firstapp.feature.map

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OverpassService {
    @GET("interpreter")
    suspend fun getGyms(
        @Query("data") query: String
    ): OverpassResponse
}

fun createOverpassService(): OverpassService {
    return Retrofit.Builder()
        .baseUrl("https://overpass-api.de/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OverpassService::class.java)
}

data class OverpassResponse(
    val elements: List<Element>
)

data class Element(
    val lat: Double,
    val lon: Double,
    val tags: Tags?
)

data class Tags(
    val name: String?
)
