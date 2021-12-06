package com.example.carwash.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.data.model.Person
import com.example.carwash.databinding.FragmentEditAccountBinding
import com.example.carwash.data.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_create_account.*

class EditAccountFragment : Fragment() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.reference

    val usuario = FirebaseAuth.getInstance()
    private var auth: FirebaseAuth? = null
    private lateinit var database: DatabaseReference
    private val user = Firebase.auth.currentUser
    private lateinit var editAccountBinding: FragmentEditAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    val dadosUsuario = Person(
        userId = System.currentTimeMillis().toString(),
        userEmail = etEmailChangeAccount.text.toString(),
        userName = etNameChangeAccount.text.toString(),
        userNumber = etTelephoneChangeAccount.text.toString(),
        userPassword = etPasswordChangeAccount.text.toString()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_account, container, false)
        navigate()
        return editAccountBinding.root
    }

    fun navigate() {
        editAccountBinding.btnVoltar.setOnClickListener {
            findNavController().navigate(R.id.nav_edit_account_to_home)
        }

    }

    private fun editAccount() {
        Util.exibirToast(requireContext(), user.toString())
        editAccountBinding.btnSaveAccount.setOnClickListener {
            Util.exibirToast(requireContext(), user.toString())
            if (!editAccountBinding.etPasswordChangeAccount.equals("")) {
                changePassword()
            }
            if (!editAccountBinding.etEmailChangeAccount.equals("")) {
                changeEmail()
            }
        }
    }


    private fun changePassword() {
        val user = Firebase.auth.currentUser
        val newPassword = editAccountBinding.etPasswordChangeAccount.text.toString()

        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Log.d(TAG, "User password updated.")
                    Util.exibirToast(requireContext(), "User password updated.")
                    //databaseReference.child("Users").child(dadosUsuario.userId).child("password").setValue(dadosUsuario.userPassword)
                } else {
                    Log.d(TAG, "error")
                    Util.exibirToast(requireContext(), "some error is happen")
                }
            }
    }

    private fun changeEmail() {
        val user = Firebase.auth.currentUser
        val newEmail = editAccountBinding.etEmailChangeAccount.text.toString()


        user!!.updateEmail(newEmail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    //databaseReference.child("Users").child(dadosUsuario.userId).child("email").setValue(dadosUsuario.userEmail)
                    Log.d(TAG, "User email address updated.")
                    Util.exibirToast(requireContext(), "Email address updated.")
                } else {
                    Log.d(TAG, "error")
                    Util.exibirToast(requireContext(), "some error is happen")
                }
            }
    }

    private fun changeTelephone() {
        val user = Firebase.auth.currentUser
        val newTelephone = editAccountBinding.etTelephoneChangeAccount.text.toString()
    }

    companion object{
        private const val TAG = "editAccount"
    }
}

