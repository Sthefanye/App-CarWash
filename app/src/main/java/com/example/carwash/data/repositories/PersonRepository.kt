package com.example.carwash.data.repositories

import android.util.Log
import com.example.carwash.data.model.Person
import com.example.carwash.data.model.Vehicle
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await

object PersonRepository: Repository() {

    fun add(person:Person){
        val ref = databaseReference.child(person.userId).child("person")

        ref.child("email").setValue(person.userEmail)
        ref.child("name").setValue(person.userName)
        ref.child("telephone").setValue(person.userNumber)
        ref.child("password").setValue(person.userPassword)
        ref.child("id").setValue(person.userId)
    }

    suspend fun get(uid: String = authReference.uid.toString()):List<Vehicle>{
        val ref  = databaseReference.child(uid).child("person")
        val result = ref.get().await()

        //val cars = result.children.forEach{ Log.d("Vehicle", it.getValue<Vehicle>()?.modelo.toString()) }
        var personData: List<Vehicle> = result.children.map { it.getValue<Vehicle>()!! }
        Log.d("Person", personData.toString())
        return personData
    }



}