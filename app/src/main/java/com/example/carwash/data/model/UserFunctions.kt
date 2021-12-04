package com.example.carwash.data.model

import android.util.Log
import com.example.carwash.model.CreateAccountData
import com.example.carwash.ui.fragments.LoginFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_create_account.*
import com.example.carwash.ui.fragments.CreateAccountFragment

class UserFunctions  {
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.reference
    val usuario = FirebaseAuth.getInstance()

    val user = Person()

    fun setUserName() {


    }
}