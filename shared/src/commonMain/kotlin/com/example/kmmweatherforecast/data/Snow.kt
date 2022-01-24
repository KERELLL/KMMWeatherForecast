package com.example.kmmweatherforecast.data


import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Snow(
    @SerialName("1h")
    val h: Double
)