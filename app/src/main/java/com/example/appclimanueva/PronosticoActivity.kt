package com.example.appclimanueva

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appclimanueva.adapter.PronosticoAdapter
import com.example.appclimanueva.databinding.ActivityPronosticoBinding
import com.example.appclimanueva.repository.ClimaRepository
import kotlinx.coroutines.launch


class PronosticoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPronosticoBinding
    private val repo = ClimaRepository()
    private lateinit var adapter: PronosticoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPronosticoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ciudad = intent.getStringExtra("ciudad") ?: "Santiago"
        adapter = PronosticoAdapter()
        binding.rvPronostico.layoutManager = LinearLayoutManager(this)
        binding.rvPronostico.adapter = adapter

        lifecycleScope.launch {
            val pronostico = repo.obtenerPronostico(ciudad)
            adapter.actualizarLista(pronostico.list)
        }
    }
}