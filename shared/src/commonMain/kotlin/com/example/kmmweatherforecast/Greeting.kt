package com.example.kmmweatherforecast

import com.example.kmmweatherforecast.data.CurrentWeatherResponse
import io.ktor.client.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import io.ktor.client.features.json.JsonFeature


class Greeting {
    private val httpClient = HttpClient(){
        install(JsonFeature){
            val json = Json{ignoreUnknownKeys = true}
            serializer = KotlinxSerializer(json)
        }
    }
    @Throws(Throwable::class)
    suspend fun greeting(city: String): String {
        return "${getCurrentWeather(city).main.temp}, ${Platform().platform}!"
    }
    private suspend fun getCurrentWeather(city: String) : CurrentWeatherResponse{
        return httpClient.get("http://api.openweathermap.org/data/2.5/weather?q=$city&appid=4bfeb4f08be3f2aa289378c8a1dd4b3f")
    }
}