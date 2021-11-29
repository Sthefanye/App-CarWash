package com.example.carwash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.carwash.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var FirebaseDatabase = FirebaseDatabase.getInstance()
        var DatabaseReference = FirebaseDatabase.getReference()
        DatabaseReference.child("teste").push().setValue("100")
    }
}