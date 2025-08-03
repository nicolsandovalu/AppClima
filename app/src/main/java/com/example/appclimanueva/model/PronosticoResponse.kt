package com.example.appclimanueva.model

import com.google.gson.annotations.SerializedName


data class PronosticoResponse(
    val list: List<PronosticoDia>,
    val city: Ciudad
)

data class PronosticoDia(
    val dt_txt: String,
    val main: Main,
    val weather: List<Weather>
)

data class Ciudad(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String
)