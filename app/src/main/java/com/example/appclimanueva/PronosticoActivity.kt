package com.example.appclimanueva

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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
import com.example.appclimanueva.utils.Constants
import kotlinx.coroutines.launch

class PronosticoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPronosticoBinding
    private val repo = ClimaRepository(this)
    // Inicializamos el adaptador directamente con una lista vacía para evitar el error 'No adapter attached'
    private val adapter = PronosticoAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPronosticoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pronóstico"

        val ciudad = intent.getStringExtra(Constants.EXTRA_CIUDAD) ?: run {
            Toast.makeText(this, "No se especificó ciudad", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        configurarRecyclerView()
        obtenerPronostico(ciudad)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Configura la RecyclerView con el adaptador y el layout manager.
     */
    private fun configurarRecyclerView() {
        binding.rvPronostico.apply {
            layoutManager = LinearLayoutManager(this@PronosticoActivity)
            // Ya no es necesario inicializar el adaptador aquí
            adapter = this@PronosticoActivity.adapter
        }
    }

    /**
     * Obtiene el pronóstico del tiempo para una ciudad y actualiza la UI.
     */
    private fun obtenerPronostico(ciudad: String) {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                binding.rvPronostico.visibility = View.GONE

                val response: PronosticoResponse = repo.obtenerPronostico(ciudad)

                Log.d("PronosticoActivity", "Respuesta de la API completa: $response")

                if (response.list.isNotEmpty()) {
                    Log.d("PronosticoActivity", "Datos recibidos: ${response.list.size} pronósticos")
                    adapter.updateData(response.list)
                    binding.rvPronostico.visibility = View.VISIBLE
                    binding.tvTituloPronostico.text = "Pronóstico para: ${response.city.name}, ${response.city.country}"
                } else {
                    Log.d("PronosticoActivity", "La lista de pronósticos está vacía.")
                    binding.tvTituloPronostico.text = "No se encontraron pronósticos para: ${ciudad}"
                    Toast.makeText(this@PronosticoActivity, "No se encontraron datos.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("PronosticoActivity", "Error al obtener el pronóstico: ${e.message}", e)
                binding.tvTituloPronostico.text = "Error al obtener el pronóstico. Inténtalo de nuevo."
                binding.rvPronostico.visibility = View.GONE
                Toast.makeText(this@PronosticoActivity, "Error de conexión o de la API", Toast.LENGTH_LONG).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
