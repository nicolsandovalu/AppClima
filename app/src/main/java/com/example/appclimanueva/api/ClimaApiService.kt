package com.example.appclimanueva.api

import com.example.appclimanueva.model.ClimaResponse
import com.example.appclimanueva.model.PronosticoDia
import com.example.appclimanueva.model.PronosticoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ClimaApiService {

    @GET("weather")
    suspend fun obtenerClimaActual(
        @Query("q") ciudad: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es"
    ): Response<ClimaResponse>

    @GET("forecast")
    suspend fun obtenerPronostico(
        @Query("q") ciudad: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es"
    ): Response<PronosticoResponse>
}