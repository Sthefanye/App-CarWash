package com.example.carwash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carwash.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        database = Firebase.database.reference//ooo

        database.child("pontos").setValue("oi")
    }}
