package com.example.afinal.retrofit.response

data class LoginResponse (
    var id:Int,
    var correo:String,
    var user:String,
    var pass:String,
    var rpta: Boolean,
    var mensaje:String
)