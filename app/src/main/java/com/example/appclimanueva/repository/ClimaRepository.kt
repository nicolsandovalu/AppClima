package com.example.appclimanueva.repository

import android.content.Context
import android.util.Log
import com.example.appclimanueva.api.ClimaApiService
import com.example.appclimanueva.api.RetrofitClient
import com.example.appclimanueva.model.ClimaResponse
import com.example.appclimanueva.model.PronosticoDia
import com.example.appclimanueva.model.PronosticoResponse
import retrofit2.Response
import kotlin.Exception


object NetworkManager {
    fun isNetworkAvailable(contexto: Context): Boolean {
        return true
    }


    fun getNetworkType(context: Context): String {
        return "WIFI"
    }
}

class ClimaRepository (private val context: Context) {

    private val api: ClimaApiService = RetrofitClient.instance.create(ClimaApiService::class.java)
    private val apiKey = "8dd22c27d86a9a004c6ccf47415ff043"
    private val appContext = context

    companion object {
        private const val TAG = "ClimaRepository"
    }

    private suspend fun <T> safeApiCall(apiCall: suspend() -> Response<T>): T {
        val response = apiCall()
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Respuesta vacía del servidor")
        } else {
            throw Exception("Error en la API: ${response.code()} - ${response.message()}")
        }
    }

    suspend fun obtenerClima(ciudad: String): ClimaResponse {
        val isOnline = NetworkManager.isNetworkAvailable(appContext)
        Log.d(TAG, "Estado de red: Online=$isOnline")
        return safeApiCall<ClimaResponse> { api.obtenerClimaActual(ciudad, apiKey) }
    }

    suspend fun obtenerPronostico(ciudad: String): PronosticoResponse {
        return safeApiCall<PronosticoResponse> { api.obtenerPronostico(ciudad, apiKey) }
    }
}