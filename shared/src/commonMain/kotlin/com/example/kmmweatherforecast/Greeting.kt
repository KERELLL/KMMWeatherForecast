package com.example.kmmweatherforecast

import io.ktor.client.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import io.ktor.client.features.json.JsonFeature


@Serializable
data class Hello(
    val string: String,
)

class Greeting {
    private val httpClient = HttpClient(){
        install(JsonFeature){
            val json = Json{ignoreUnknownKeys = true}
            serializer = KotlinxSerializer(json)
        }
    }
    @Throws(Throwable::class)
    suspend fun greeting(): String {
        return "${getCurrentWeather().random().string}, ${Platform().platform}!"
    }
    private suspend fun getCurrentWeather() : List<Hello>{
        return httpClient.get("https://gitcdn.link/cdn/KaterinaPetrova/greeting/7d47a42fc8d28820387ac7f4aaf36d69e434adc1/greetings.json")
    }
}