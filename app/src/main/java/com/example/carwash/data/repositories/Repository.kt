package com.example.carwash.data.repositories

import com.example.carwash.data.util.DialogProgress
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

open class Repository {

    val databaseReference = FirebaseDatabase.getInstance().reference.child("Users")
    val dataServiceReference = FirebaseDatabase.getInstance().reference.child("Services")
    val dataAgendamentoReference = FirebaseDatabase.getInstance().reference.child("Agendamento")
    val authReference = FirebaseAuth.getInstance()

    val dialogProgress = DialogProgress()

}