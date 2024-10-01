package com.example.afinal.view.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.afinal.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button_ayuda = findViewById<Button>(R.id.btnAyuda)
        button_ayuda.setOnClickListener {
            val intent = Intent(this, AyudaActivity::class.java)
            startActivity(intent)
        }

        val button_registro = findViewById<Button>(R.id.buttonRegistrar)
        button_registro.setOnClickListener {
                val intent = Intent(this, RegistroUsuarioActivity::class.java)
                startActivity(intent)
        }
        val button_ingreso = findViewById<Button>(R.id.buttonIngresar)
        button_ingreso.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }
        val text_olvido = findViewById<TextView>(R.id.tvOlvidoContrasena)
        text_olvido.setOnClickListener {
            val intent = Intent(this, RecuperarContrasenaActivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets



        }
    }

}