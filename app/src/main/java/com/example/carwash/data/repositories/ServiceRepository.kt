package com.example.carwash.data.repositories

import android.util.Log
import com.example.carwash.data.model.Person
import com.example.carwash.data.model.Service
import com.example.carwash.data.model.Vehicle
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await


object ServiceRepository: Repository() {

    fun add(service: Service){
        val ref = FirebaseDatabase.getInstance().reference.child("Services")
            .child(service.serviceID)

        ref.child("cod").setValue(service.serviceID)
        ref.child("desc").setValue(service.serviceDesc)
        ref.child("price").setValue(service.servicePrice)
        ref.child("title").setValue(service.serviceTitle)
    }

    fun addAgendamento(data: String, hour:String, service:String, placa:String, userUid: String = authReference.uid.toString()){
        val ref = FirebaseDatabase.getInstance().reference.child("Agendamento")
            .child(placa)

        ref.child("data").setValue(data)
        ref.child("hour").setValue(hour)
        ref.child("service").setValue(service)
        ref.child("placa").setValue(placa)
    }

    suspend fun get(sid: String = "Services"):List<Service>{
        val ref = FirebaseDatabase.getInstance().reference.child(sid)
        val result = ref.get().await()

        var serviceData: List<Service> = result.children.map { it.getValue<Service>()!! }
        Log.d("Service", serviceData.toString())
        Log.d("Service", "ServiceRepository")
        return serviceData
    }
}