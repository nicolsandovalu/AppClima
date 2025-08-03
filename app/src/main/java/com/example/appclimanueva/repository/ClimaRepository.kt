package com.example.appclimanueva.repository

import com.example.appclimanueva.api.ClimaApiService
import com.example.appclimanueva.api.RetrofitClient
import com.example.appclimanueva.model.ClimaResponse
import com.example.appclimanueva.model.PronosticoResponse
import kotlin.Exception


class ClimaRepository {

    private val api = RetrofitClient.instance.create(ClimaApiService::class.java)

    private val apiKey = "8dd22c27d86a9a004c6ccf47415ff043"

    suspend fun obtenerClima(ciudad: String): ClimaResponse {
        return try {
            if (apiKey == "8dd22c27d86a9a004c6ccf47415ff043") {
                throw Exception("Por favor configura tu API key de OpenWeatherMap")
            }
            api.obtenerClimaActual(ciudad, apiKey)
        } catch (e: Exception) {
            throw Exception("Error al obtener el clima: ${e.message}")
        }
    }

    suspend fun obtenerPronostico(ciudad: String): PronosticoResponse {
        return try {
            if (apiKey == "8dd22c27d86a9a004c6ccf47415ff043") {
                throw Exception("Por favor configura tu API key de OpenWeatherMap")
            }
            api.obtenerPronostico(ciudad, apiKey)
        } catch (e: Exception) {
            throw Exception("Error al obtener el pronóstico: ${e.message}")
        }
    }
}