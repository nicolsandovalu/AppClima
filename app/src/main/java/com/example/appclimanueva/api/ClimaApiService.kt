package com.example.appclimanueva.api

import com.example.appclimanueva.model.ClimaResponse
import com.example.appclimanueva.model.PronosticoResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ClimaApiService {

    @GET("weather")
    suspend fun obtenerClimaActual(
        @Query("q") ciudad: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es"
    ): ClimaResponse

    @GET("forecast")
    suspend fun obtenerPronostico(
        @Query("q") ciudad: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es"
    ): PronosticoResponse
}