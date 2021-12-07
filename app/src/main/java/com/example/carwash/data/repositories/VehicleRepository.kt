package com.example.carwash.data.repositories

import android.util.Log
import com.example.carwash.data.model.Vehicle
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await

object VehicleRepository: Repository() {


    fun add(vehicle: Vehicle, uid: String = authReference.uid.toString()){
        val ref  = databaseReference.child(uid).child("vehicles").child(vehicle.placa)

        ref.child("modelo").setValue(vehicle.modelo)
        ref.child("cor").setValue(vehicle.cor)
        ref.child("placa").setValue(vehicle.placa)
        ref.child("ano").setValue(vehicle.ano)

        Log.d("Vehicle", "Adicionado com sucesso")
    }

    suspend fun get(uid: String = authReference.uid.toString()):List<Vehicle>{
        val ref  = databaseReference.child(uid).child("vehicles")
        val result = ref.get().await()

        //val cars = result.children.forEach{ Log.d("Vehicle", it.getValue<Vehicle>()?.modelo.toString()) }
        var cars: List<Vehicle> = result.children.map { it.getValue<Vehicle>()!! }
        Log.d("Vehicle", cars.toString())
        return cars
    }

}