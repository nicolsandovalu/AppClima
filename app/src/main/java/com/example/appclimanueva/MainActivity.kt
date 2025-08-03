package com.example.appclimanueva

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appclimanueva.databinding.ActivityMainBinding
import com.example.appclimanueva.model.ClimaResponse
import com.example.appclimanueva.repository.ClimaRepository
import com.example.appclimanueva.utils.Constants
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var climaRepository: ClimaRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        climaRepository = ClimaRepository(this)

        binding.btnBuscar.setOnClickListener {
            val ciudad = binding.etCiudad.text.toString().trim()
            if (ciudad.isNotEmpty()) {
                obtenerClima(ciudad)
            } else {
                Toast.makeText(this, "Por favor, ingresa una ciudad", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPronostico.setOnClickListener {
            val ciudad = binding.etCiudad.text.toString().trim()
            if (ciudad.isNotEmpty()) {
                val intent = Intent(this, PronosticoActivity::class.java)
                intent.putExtra(Constants.EXTRA_CIUDAD, ciudad)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, ingresa una ciudad para ver el pronóstico", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun obtenerClima(ciudad: String) {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                binding.tvResultado.text = ""

                val clima: ClimaResponse = climaRepository.obtenerClima(ciudad)
                binding.tvResultado.text = "Ciudad: ${clima.name}\nTemp: ${clima.main.temp}°C\nDesc: ${clima.weather[0].description}"
            } catch (e: Exception) {
                binding.tvResultado.text = "Error: ${e.localizedMessage}"
                Toast.makeText(this@MainActivity, "Error al obtener el clima.", Toast.LENGTH_LONG).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
