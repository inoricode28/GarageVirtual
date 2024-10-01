package com.example.afinal.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.afinal.R
import com.example.afinal.databinding.ActivityMainBinding
import com.example.afinal.retrofit.response.LoginResponse
import com.example.afinal.util.AppMensaje
import com.example.afinal.util.TipoMensaje
import com.example.afinal.viewmodel.AuthViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Especificar el tipo explícitamente al instanciar ViewModelProvider
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        // Configurar listeners
        binding.buttonIngresar.setOnClickListener(this)
        binding.buttonRegistrar.setOnClickListener(this)
        binding.tvOlvidoContrasena.setOnClickListener(this)
        binding.btnAyuda.setOnClickListener(this)

        // Observador para el loginResponse
        authViewModel.loginResponse.observe(this, Observer { response ->
            obtenerDatosLogin(response)
        })
    }

    private fun obtenerDatosLogin(response: LoginResponse) {
        if (response.rpta) {
            startActivity(Intent(applicationContext, PrincipalActivity::class.java))
        } else {
            AppMensaje.enviarMensaje(binding.root, response.mensaje, TipoMensaje.ERROR)
        }
        // Habilitar los botones después de la respuesta
        binding.buttonIngresar.isEnabled = true
        binding.buttonRegistrar.isEnabled = true
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonIngresar -> autenticarUsuario()
            R.id.buttonRegistrar -> startActivity(Intent(applicationContext, RegistroActivity::class.java))
            R.id.tvOlvidoContrasena -> startActivity(Intent(applicationContext, RecuperarContrasenaActivity::class.java))
            R.id.btnAyuda -> startActivity(Intent(applicationContext, AyudaActivity::class.java))
        }
    }

    private fun autenticarUsuario() {
        // Deshabilitar botones mientras se procesa el login
        binding.buttonIngresar.isEnabled = false
        binding.buttonRegistrar.isEnabled = false

        // Llamar a la función de login en el ViewModel
        authViewModel.login(
            binding.etUsuario.text.toString(),
            binding.etPassword.text.toString()
        )
    }
}
