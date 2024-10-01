package com.example.afinal.retrofit.request

data class RegistroRequest(
    var nombre:String,
    var apellido:String,
    val correo:String,
    var celular:String,
    val user:String,
    val pass:String
)