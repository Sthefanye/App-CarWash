package com.example.carwash.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.carwash.R
import com.example.carwash.databinding.FragmentCreateAccountBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateAccountFragment : Fragment() {
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.reference

    val usuarios = databaseReference.child("users")

    private lateinit var createAccountBinding: FragmentCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createAccountBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_account, container, false)
        return createAccountBinding.root
    }
}