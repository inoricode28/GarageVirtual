package com.example.afinal.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
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
        listarVehiculos() // Listar todos los vehículos al iniciar la actividad

        // Observar la respuesta de eliminación
        viewModel.eliminarVehiculoResponse.observe(this, Observer { eliminado ->
            if (eliminado) {
                Toast.makeText(this, "Vehículo eliminado correctamente", Toast.LENGTH_SHORT).show()
                listarVehiculos() // Actualizar la lista de vehículos después de eliminar
            } else {
                Toast.makeText(this, "Error al eliminar el vehículo", Toast.LENGTH_SHORT).show()
            }
        })

        // Acción del botón de registrar nuevo vehículo
        binding.buttonRegistrarVehiculo.setOnClickListener(this)

        // Acción del botón de buscar por placa
        binding.btnSearch.setOnClickListener {
            val placa = binding.searchField.text.toString().trim()
            if (placa.isNotEmpty()) {
                buscarVehiculoPorPlaca(placa)
            } else {
                listarVehiculos() // Listar todos los vehículos si el campo está vacío
                Toast.makeText(this, "Ingrese una placa valida", Toast.LENGTH_SHORT).show()
            }
        }

        // Listener para detectar el evento de "Enter" en el searchField
        binding.searchField.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                val placa = binding.searchField.text.toString().trim()
                if (placa.isNotEmpty()) {
                    buscarVehiculoPorPlaca(placa)
                } else {
                    listarVehiculos() // Listar todos los vehículos si el campo está vacío
                }
                true
            } else {
                false
            }
        }
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
        // Llamar al método para eliminar el vehículo en el ViewModel
        viewModel.eliminarVehiculo(vehiculo.id)
    }

    private fun buscarVehiculoPorPlaca(placa: String) {
        viewModel.buscarVehiculoPorPlaca(placa).observe(this, Observer { vehiculos ->
            if (vehiculos != null && vehiculos.isNotEmpty()) {
                setupVehiculoAdapter(vehiculos)
            } else {
                Toast.makeText(this, "No se encontró vehículo con esa placa", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun listarVehiculos() {
        viewModel.listarVehiculo().observe(this, Observer { vehiculos ->
            if (vehiculos != null) {
                setupVehiculoAdapter(vehiculos)
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_registrar_vehiculo -> startActivity(Intent(applicationContext, RegistroActivity::class.java))
        }
    }
}
