package com.example.carwash.data.repositories

import com.example.carwash.data.model.Person

object PersonRepository: Repository() {

    fun add(person:Person){
        val ref = databaseReference.child(person.userId).child("person")

        ref.child("email").setValue(person.userEmail)
        ref.child("name").setValue(person.userName)
        ref.child("telephone").setValue(person.userNumber)
        ref.child("password").setValue(person.userPassword)
        ref.child("id").setValue(person.userId)
    }

    fun get(){

    }


}