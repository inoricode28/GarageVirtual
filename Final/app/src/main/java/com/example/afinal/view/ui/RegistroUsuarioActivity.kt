package com.example.afinal.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.afinal.databinding.ActivityRegistroUsuarioBinding
import com.example.afinal.viewmodel.AuthViewModel
import androidx.lifecycle.Observer
import com.example.afinal.R
import com.example.afinal.retrofit.response.RegistroResponse
import com.example.afinal.util.AppMensaje
import com.example.afinal.util.TipoMensaje

class RegistroUsuarioActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegistroUsuarioBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
        binding = ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Especificar el tipo explícitamente al instanciar ViewModelProvider
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        // Configurar los botones en el listeners
        binding.btnRegistrar.setOnClickListener(this)
        binding.btnVolver.setOnClickListener(this)

        // Observador para el loginResponse
        authViewModel.registroResponse.observe(this, Observer { response ->
            obtenerDatosRegistro(response)
        })

    }

    private fun obtenerDatosRegistro(response: RegistroResponse) {
        binding.btnVolver.isEnabled = true
        binding.btnRegistrar.isEnabled = true
        AppMensaje.enviarMensaje(binding.root, response.mensaje, TipoMensaje.ADVERTENCIA)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnVolver -> startActivity(Intent(applicationContext, MainActivity::class.java))
            R.id.btnRegistrar -> registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        binding.btnVolver.isEnabled = false
        binding.btnRegistrar.isEnabled = false
        authViewModel.registro(
            binding.etNombre.text.toString(),
            binding.etApellido.text.toString(),
            binding.etCorreo.text.toString(),
            binding.etCelular.text.toString(),
            binding.etUser.text.toString(),
            binding.etContrasena.text.toString())
    }


}