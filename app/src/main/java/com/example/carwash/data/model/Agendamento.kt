package com.example.carwash.data.model

open class Agendamento(
    var data: String = "",
    var hour: String = "",
    var service: String = "",
    var status: String = "",
    var placa: String = "",
    var vehicle: Vehicle? = null
)
