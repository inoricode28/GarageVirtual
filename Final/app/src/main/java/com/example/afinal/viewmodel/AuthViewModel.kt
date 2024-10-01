package com.example.afinal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.afinal.repository.AuthRepository
import com.example.afinal.retrofit.request.LoginRequest
import com.example.afinal.retrofit.request.RegistroRequest
import com.example.afinal.retrofit.request.RegistroVehiculoRequest
import com.example.afinal.retrofit.response.LoginResponse
import com.example.afinal.retrofit.response.RegistroResponse
import com.example.afinal.retrofit.response.RegistroVehiculoResponse
import com.example.afinal.retrofit.response.VehiculoResponse

class AuthViewModel: ViewModel() {

    var loginResponse: LiveData<LoginResponse>
    var registroResponse: LiveData<RegistroResponse>
    var registroVehiculoResponse: LiveData<RegistroVehiculoResponse>

    private var repository = AuthRepository()
    init {
        loginResponse = repository.loginResponse
        registroResponse = repository.registroResponse
        registroVehiculoResponse = repository.registroVehiculoResponse

    }

    fun login(user:String, pass:String){
        loginResponse = repository.login(LoginRequest(user, pass))
    }

    fun registro(nombre:String, apellido:String,
                 correo:String, celular:String,
                 user:String,pass: String){
        registroResponse = repository.registro(
            RegistroRequest(nombre,apellido,correo,
            celular, user, pass)
        )
    }

    fun regitroVehiculo(
        placa: String,
        marca: String,
        modelo: String,
        kilometraje: String

        ){
        registroVehiculoResponse= repository.registroVehiculo(RegistroVehiculoRequest(
            placa,
            marca,
            modelo,
            kilometraje
        ))

    }

    fun listarVehiculo(): LiveData<List<VehiculoResponse>>{
        return repository.listadoVehiculo()
    }


}