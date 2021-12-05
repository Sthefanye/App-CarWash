package com.example.carwash.data.model

open class Person(
    var userId: String = "",
    var userName: String = "",
    var userEmail: String = "",
    var userPassword: String = "",
    var userNumber: String = "")
{
    fun getName():String{
        return userName
    }

    fun getEmail():String{
        return userEmail
    }
    fun getPassword():String{
        return userPassword
    }
    fun getNumber(): String {
        return userNumber
    }
}