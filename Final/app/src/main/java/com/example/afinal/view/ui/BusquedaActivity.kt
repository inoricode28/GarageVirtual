package com.example.afinal.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afinal.R
import com.example.afinal.databinding.ActivityBusquedaBinding
import com.example.afinal.retrofit.response.VehiculoResponse
import com.example.afinal.view.adapter.VehiculoAdapter
import com.example.afinal.viewmodel.AuthViewModel

class eBusquedaActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityBusquedaBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusquedaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        setupRecyclerView()

        // Observar la lista de vehículos
        viewModel.listarVehiculo().observe(this, Observer { vehiculos ->
            if (vehiculos != null) {
                setupVehiculoAdapter(vehiculos)
            }
        })

        // Acción del botón de búsqueda (puedes agregar lógica para la búsqueda aquí)
        binding.btnSearch.setOnClickListener {
            // Implementar la lógica de búsqueda si es necesario
        }

        // Acción del botón de registrar nuevo vehículo
        binding.buttonRegistrarVehiculo.setOnClickListener(this)

    }

    private fun setupRecyclerView() {
        binding.recyclerViewVehiculos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewVehiculos.setHasFixedSize(true)
    }

    private fun setupVehiculoAdapter(vehiculos: List<VehiculoResponse>) {
        val adapter = VehiculoAdapter(vehiculos) { vehiculo ->
            // Acción cuando se presiona "Eliminar"
            eliminarVehiculo(vehiculo)
        }
        binding.recyclerViewVehiculos.adapter = adapter
    }

    private fun eliminarVehiculo(vehiculo: VehiculoResponse) {
        // Implementar lógica de eliminación
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.button_registrar_vehiculo -> startActivity(Intent(applicationContext, RegistroActivity::class.java))
        }
    }
}
