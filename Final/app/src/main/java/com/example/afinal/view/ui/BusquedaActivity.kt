package com.example.afinal.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.afinal.databinding.ActivityBusquedaBinding

class eBusquedaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusquedaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusquedaBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}