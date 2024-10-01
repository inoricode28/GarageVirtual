package com.example.afinal.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.afinal.R
import com.example.afinal.databinding.ActivityPrincipalBinding


class PrincipalActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var binding: ActivityPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar los botones en el listeners
        binding.btnVehicles.setOnClickListener(this)
        binding.btnMechanic.setOnClickListener(this)
        binding.btnMaintenance.setOnClickListener(this)
    }

    // Funcion que me permite moverme entre vistas
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_vehicles -> startActivity(Intent(applicationContext, eBusquedaActivity::class.java))
        }
    }

}