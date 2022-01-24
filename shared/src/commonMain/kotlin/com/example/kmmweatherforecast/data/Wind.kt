package com.example.kmmweatherforecast.data

import kotlinx.serialization.Serializable


@Serializable
data class Wind(
    val speed: Double,
    val deg: Int,
)