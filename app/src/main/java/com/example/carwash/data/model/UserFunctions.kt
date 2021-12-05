package com.example.carwash.data.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserFunctions  {
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.reference
    val usuario = FirebaseAuth.getInstance()

    val user = Person()

    fun setUserName() {


    }
}