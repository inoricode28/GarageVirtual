package com.example.afinal.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.afinal.databinding.ActivityRegistroBinding
import com.example.afinal.viewmodel.AuthViewModel
import androidx.lifecycle.Observer
import com.example.afinal.R
import com.example.afinal.retrofit.response.RegistroVehiculoResponse
import com.example.afinal.util.AppMensaje
import com.example.afinal.util.TipoMensaje

class RegistroActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Especificar el tipo explícitamente al instanciar ViewModelProvider
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        // Configurar los botones en el listeners
        binding.btnRegistro.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)

        // Observador para el loginResponse
        authViewModel.registroVehiculoResponse.observe(this, Observer { response ->
            obtenerDatosRegistro(response)
        })
    }

    private fun obtenerDatosRegistro(response: RegistroVehiculoResponse) {
        binding.btnBack.isEnabled = true
        binding.btnRegistro.isEnabled = true
        AppMensaje.enviarMensaje(binding.root, response.mensaje, TipoMensaje.ADVERTENCIA)
    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.btnBack -> startActivity(Intent(applicationContext, eBusquedaActivity::class.java))
            R.id.btnRegistro -> registrarVehiculo()
        }
    }

    private fun registrarVehiculo() {
        binding.btnBack.isEnabled = false
        binding.btnRegistro.isEnabled = false
        authViewModel.regitroVehiculo(
            binding.etPlaca.text.toString(),
            binding.etMarca.text.toString(),
            binding.etModelo.text.toString(),
            binding.etKilometraje.text.toString()
        )
    }

}