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




}