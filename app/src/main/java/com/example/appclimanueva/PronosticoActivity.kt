package com.example.appclimanueva

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appclimanueva.adapter.PronosticoAdapter
import com.example.appclimanueva.databinding.ActivityPronosticoBinding
import com.example.appclimanueva.model.PronosticoDia
import com.example.appclimanueva.model.PronosticoResponse
import com.example.appclimanueva.repository.ClimaRepository
import kotlinx.coroutines.launch


class PronosticoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPronosticoBinding
    private val repo = ClimaRepository(this)
    private lateinit var adapter: PronosticoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPronosticoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el Toolbar como ActionBar y añadir la flecha de retroceso
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pronóstico"


        val ciudad = intent.getStringExtra("ciudad") ?: run {
            Toast.makeText(this, "No se especificó ciudad", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        configurarRecyclerView()
        obtenerPronostico(ciudad)
    }

    private fun configurarRecyclerView() {
        //se pasa una lista vacía con el tipo de datos correcto para el adapter
        adapter = PronosticoAdapter(mutableListOf<PronosticoDia>())
        binding.rvPronostico.apply {
            layoutManager = LinearLayoutManager(this@PronosticoActivity)
            this.adapter = this@PronosticoActivity.adapter
        }
    }

    private fun obtenerPronostico(ciudad: String) {
        lifecycleScope.launch {
            try {
                // Muestra el ProgressBar y limpia el resultado anterior
                binding.progressBar.visibility = View.VISIBLE

                val response: PronosticoResponse = repo.obtenerPronostico(ciudad)

                response.list.let { listaPronosticos ->
                    adapter.updateData(listaPronosticos)

                    binding.tvTituloPronostico.text = "Pronóstico: ${response.city.name}, ${response.city.country}"
                }
            } catch (e: Exception) {
                Toast.makeText(this@PronosticoActivity, "Error de conexión o de la API", Toast.LENGTH_LONG).show()
                Log.e("API_ERROR", "Error al obtener pronóstico", e)
            } finally {
                // Oculta el ProgressBar al finalizar
                binding.progressBar.visibility = View.GONE

            }
        }
    }
}