package com.example.afinal.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.afinal.retrofit.GarageCliente
import com.example.afinal.retrofit.request.LoginRequest
import com.example.afinal.retrofit.request.RegistroRequest
import com.example.afinal.retrofit.request.RegistroVehiculoRequest
import com.example.afinal.retrofit.response.LoginResponse
import com.example.afinal.retrofit.response.RegistroResponse
import com.example.afinal.retrofit.response.RegistroVehiculoResponse
import com.example.afinal.retrofit.response.VehiculoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    var loginResponse= MutableLiveData<LoginResponse>()
    var registroResponse = MutableLiveData<RegistroResponse>()
    var registroVehiculoResponse = MutableLiveData<RegistroVehiculoResponse>()
    var vehiculoResponse = MutableLiveData<List<VehiculoResponse>>()
    var eliminarVehiculoResponse = MutableLiveData<Boolean>() // Para monitorear si la eliminación fue exitosa


    fun login(loginRequest: LoginRequest): MutableLiveData<LoginResponse> {
        val call: Call<LoginResponse> = GarageCliente.retrofitService.login(loginRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginResponse.value = response.body()
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }
        })
        return loginResponse
    }

    fun registro(registroRequest: RegistroRequest): MutableLiveData<RegistroResponse> {
        val call: Call<RegistroResponse> = GarageCliente.retrofitService.registrar(registroRequest)
        call.enqueue(object : Callback<RegistroResponse> {
            override fun onResponse(call: Call<RegistroResponse>, response: Response<RegistroResponse>) {
                registroResponse.value = response.body()
            }
            override fun onFailure(call: Call<RegistroResponse>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }
        })
        return registroResponse
    }

    fun registroVehiculo(registroVehiculoRequest: RegistroVehiculoRequest): MutableLiveData<RegistroVehiculoResponse> {
        val call: Call<RegistroVehiculoResponse> = GarageCliente.retrofitService.registrarVehiculo(registroVehiculoRequest)
        call.enqueue(object : Callback<RegistroVehiculoResponse> {
            override fun onResponse(call: Call<RegistroVehiculoResponse>, response: Response<RegistroVehiculoResponse>) {
                registroVehiculoResponse.value = response.body()
            }
            override fun onFailure(call: Call<RegistroVehiculoResponse>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }
        })
        return registroVehiculoResponse
    }

    fun listadoVehiculo(): MutableLiveData<List<VehiculoResponse>>{
        val call : Call<List<VehiculoResponse>> = GarageCliente.retrofitService.listarVehiculo()
        call.enqueue(object : Callback<List<VehiculoResponse>>{
            override fun onResponse(
                call: Call<List<VehiculoResponse>>,
                response: Response<List<VehiculoResponse>>
            ) {
                vehiculoResponse.value = response.body()
            }
            override fun onFailure(call: Call<List<VehiculoResponse>>, t: Throwable) {
                Log.i("ErrorListMascota", t.message.toString())
            }
        })
        return vehiculoResponse
    }

    // Eliminar vehículo
    fun eliminarVehiculo(id: Int): MutableLiveData<Boolean> {
        val call: Call<Void> = GarageCliente.retrofitService.eliminarVehiculo(id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    eliminarVehiculoResponse.value = true // Éxito al eliminar
                } else {
                    eliminarVehiculoResponse.value = false // Error en la respuesta
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("ErrorEliminarVehiculo", t.message.toString())
                eliminarVehiculoResponse.value = false // Error en la eliminación
            }
        })
        return eliminarVehiculoResponse
    }

    fun buscarVehiculoPorPlaca(placa: String): MutableLiveData<List<VehiculoResponse>> {
        val vehiculoResponse = MutableLiveData<List<VehiculoResponse>>()
        val call: Call<List<VehiculoResponse>> = GarageCliente.retrofitService.buscarVehiculoPorPlaca(placa)
        call.enqueue(object : Callback<List<VehiculoResponse>> {
            override fun onResponse(call: Call<List<VehiculoResponse>>, response: Response<List<VehiculoResponse>>) {
                vehiculoResponse.value = response.body()
            }
            override fun onFailure(call: Call<List<VehiculoResponse>>, t: Throwable) {
                Log.e("ErrorBuscarVehiculo", t.message.toString())
            }
        })
        return vehiculoResponse
    }




}