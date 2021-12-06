package com.example.carwash.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

open class Repository {

    val databaseReference = FirebaseDatabase.getInstance().reference.child("Users")
    val authReference = FirebaseAuth.getInstance()

}