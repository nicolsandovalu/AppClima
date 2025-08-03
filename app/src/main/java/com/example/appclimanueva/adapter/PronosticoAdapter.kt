package com.example.appclimanueva.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appclimanueva.R
import com.example.appclimanueva.model.PronosticoDia
import android.widget.ImageView



class PronosticoAdapter(private val lista: MutableList<PronosticoDia>) : RecyclerView.Adapter<PronosticoAdapter.ViewHolder>() {

    fun updateData(nueva: List<PronosticoDia>) {
        lista.clear()
        lista.addAll(nueva)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_dia_pronostico, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: PronosticoDia) {
            val tvFecha = itemView.findViewById<TextView>(R.id.tvFecha)
            val tvTemp = itemView.findViewById<TextView>(R.id.tvTemp)
            val tvDesc = itemView.findViewById<TextView>(R.id.tvDesc)
            val ivIconoClima = itemView.findViewById<ImageView>(R.id.ivIconoClima)


            tvFecha.text = item.dt_txt
            tvTemp.text = "${item.main.temp}°C"
            tvDesc.text = item.weather[0].description


            // Cargar el icono del clima usando Glide
            val iconUrl = "https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png"
            Glide.with(itemView.context)
                .load(iconUrl)
                .into(ivIconoClima)
        }
    }
}
