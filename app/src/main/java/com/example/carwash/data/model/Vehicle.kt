package com.example.carwash.data.model

import java.time.Year

class Vehicles(
    var vehicleModel:String,
    var vehicleColor:String,
    var vehicleLicense:String,
    var vehicleYear: Year
){
    fun getModel():String{
        return vehicleModel
    }

    fun getColor():String{
        return vehicleColor
    }

    fun getLicense():String{
        return vehicleLicense
    }

    fun getYear():Year{
        return vehicleYear
    }
}