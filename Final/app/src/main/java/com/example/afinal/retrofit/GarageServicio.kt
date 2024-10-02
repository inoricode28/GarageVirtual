package com.example.afinal.retrofit

import com.example.afinal.retrofit.request.LoginRequest
import com.example.afinal.retrofit.request.RegistroRequest
import com.example.afinal.retrofit.request.RegistroVehiculoRequest
import com.example.afinal.retrofit.response.LoginResponse
import com.example.afinal.retrofit.response.RegistroResponse
import com.example.afinal.retrofit.response.RegistroVehiculoResponse
import com.example.afinal.retrofit.response.VehiculoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GarageServicio {

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("usuario")
    fun registrar(@Body registroRequest: RegistroRequest): Call<RegistroResponse>

    @POST("vehiculo")
    fun registrarVehiculo(@Body registroVehiculoRequest: RegistroVehiculoRequest): Call<RegistroVehiculoResponse>

    @GET("vehiculo")
    fun listarVehiculo(): Call<List<VehiculoResponse>>

    @DELETE("vehiculo/{id}")
    fun eliminarVehiculo(@Path("id") id: Int): Call<Void>

    @GET("vehiculo/placa/{placa}")
    fun buscarVehiculoPorPlaca(@Path("placa") placa: String): Call<List<VehiculoResponse>>
}