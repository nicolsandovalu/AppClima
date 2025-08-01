package com.example.appclimanueva.model

import com.google.gson.annotations.SerializedName


data class PronosticoResponse(
    val list: List<PronosticoDia>
)

data class PronosticoDia(
    val dt_txt: String,
    val main: Main,
    val weather: List<Weather>
)

