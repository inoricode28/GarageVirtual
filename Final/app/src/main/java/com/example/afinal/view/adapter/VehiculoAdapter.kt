package com.example.afinal.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.databinding.ItemVehiculoBinding
import com.example.afinal.retrofit.response.VehiculoResponse

class VehiculoAdapter(
    private val vehiculoList: List<VehiculoResponse>,
    private val onDeleteClick: (VehiculoResponse) -> Unit
) : RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder>() {

    inner class VehiculoViewHolder(private val binding: ItemVehiculoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vehiculo: VehiculoResponse) {
            // Asignar valores a los elementos del layout usando View Binding
            binding.tvplaca.text = vehiculo.Placa
            binding.tvmarca.text = vehiculo.Marca
            binding.tvmodelo.text = vehiculo.Modelo
            binding.tvkilometraje.text = vehiculo.Kilometraje

            // Acción del botón "Eliminar"
            binding.btndelete.setOnClickListener {
                onDeleteClick(vehiculo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoViewHolder {
        val binding = ItemVehiculoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehiculoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehiculoViewHolder, position: Int) {
        holder.bind(vehiculoList[position])
    }

    override fun getItemCount(): Int {
        return vehiculoList.size
    }
}
