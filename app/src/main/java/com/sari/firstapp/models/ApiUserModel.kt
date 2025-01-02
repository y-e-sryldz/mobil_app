package com.sari.firstapp.models

data class ApiUserModel(
    val id: String? = null,
    val name: String,
    val email: String,
    val height: Double,
    val weight: Double,
    val bmi: Double
)