package com.example.carwash.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.data.model.Person
import com.example.carwash.data.model.Vehicle
import com.example.carwash.data.repositories.PersonRepository
import com.example.carwash.data.repositories.Repository
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.databinding.FragmentEditAccountBinding
import com.example.carwash.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_create_account.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.ArrayList

class EditAccountFragment : Fragment() {

    private val user = PersonRepository.authReference.currentUser

    private lateinit var editAccountBinding: FragmentEditAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_account, container, false)
        navigate()
        editAccount()
        GlobalScope.async {loadData()}
        return editAccountBinding.root
    }

    fun navigate() {
        editAccountBinding.btnVoltar.setOnClickListener {
            findNavController().navigate(R.id.nav_edit_account_to_home)
        }

    }

    private fun editAccount() {
        editAccountBinding.btnSaveAccount.setOnClickListener {
            if (etEmailChangeAccount.text.toString().trim() != "" &&
                etPasswordChangeAccount.text.toString().trim() != ""
            ) {
                PersonRepository.authReference.uid?.let { it1 ->
                    Util.exibirToast(
                        requireContext(),
                        it1
                    )
                }
                if (!editAccountBinding.etPasswordChangeAccount.equals("")) {
                    changePassword()
                }
                if (!editAccountBinding.etEmailChangeAccount.equals("")) {
                    changeEmail()
                }
                Util.exibirToast(requireContext(), "Atualizado com sucesso")
            }else{
                Util.exibirToast(requireContext(),"Preencher todos os dados")
            }
        }
    }


    private fun changePassword() {
        val newPassword = editAccountBinding.etPasswordChangeAccount.text.toString()

        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Log.d(TAG, "User password updated.")
                    //databaseReference.child("Users").child(dadosUsuario.userId).child("password").setValue(dadosUsuario.userPassword)
                } else {
                    Log.d(TAG, "error")
                    Util.exibirToast(requireContext(), "Erro ao gerar senha")
                }
            }
    }

    private fun changeEmail() {
        val newEmail = editAccountBinding.etEmailChangeAccount.text.toString()


        user!!.updateEmail(newEmail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    //databaseReference.child("Users").child(dadosUsuario.userId).child("email").setValue(dadosUsuario.userEmail)
                    Log.d(TAG, "User email address updated.")

                } else {
                    Log.d(TAG, "error Email")
                }
            }
    }

    private fun changeTelephone() {
        val user = Firebase.auth.currentUser
        val newTelephone = editAccountBinding.etTelephoneChangeAccount.text.toString()
    }

    private fun loadData(){
        val ref  = PersonRepository.databaseReference.child(PersonRepository.authReference.uid.toString())
        ref.get().addOnCompleteListener { task ->

            val result = task.result?.children
            val list = ArrayList<Person>()
            result?.forEach {
                val personData = it.getValue<Person>()

                Log.d("Person", it.value.toString())
                personData?.let { it1 -> list.add( it1) }
            }
        }
    }

    companion object{
        private const val TAG = "editAccount"
    }
}

