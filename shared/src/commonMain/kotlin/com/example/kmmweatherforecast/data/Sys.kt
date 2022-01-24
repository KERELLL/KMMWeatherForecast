package com.example.kmmweatherforecast.data


import kotlinx.serialization.Serializable

@Serializable
data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Int,
    val sunset: Int
)