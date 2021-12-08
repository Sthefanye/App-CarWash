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
import com.example.carwash.data.model.Vehicle
import com.example.carwash.data.repositories.PersonRepository
import com.example.carwash.data.repositories.Repository
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.data.util.DialogProgress
import com.example.carwash.databinding.FragmentEditAccountBinding
import com.example.carwash.data.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
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
    private val ref  = PersonRepository.databaseReference
        .child(PersonRepository.authReference.uid.toString())
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
                }
                //Editar Email
                if (!editAccountBinding.etEmailChangeAccount.equals("")) {
                    changeEmail()
                }

                //Editar Senha
                if (!editAccountBinding.etPasswordChangeAccount.equals("")) {
                    changePassword()
                    changeName()
                    changeTelephone()
                }

                Util.exibirToast(requireContext(), "Atualizado com sucesso")
                findNavController().navigate(R.id.nav_edit_account_to_home)
            }else{
                Util.exibirToast(requireContext(),"Preencher todos os dados")
            }
        }
    }

    private fun loadData(){
        ref.get().addOnCompleteListener { task ->

            val result = task.result?.child("person")
            val personData = result?.getValue<Person>()
            personData?.let { it1 ->
                editAccountBinding.etNameChangeAccount.setText(personData.name)
                editAccountBinding.etEmailChangeAccount.setText(personData.email)
                editAccountBinding.etPasswordChangeAccount.setText(personData.password)
                editAccountBinding.etTelephoneChangeAccount.setText(personData.telephone)
            }
        }
    }

    private  fun changeName(){
        val newName = editAccountBinding.etNameChangeAccount.text.toString()

        ref.child("person").child("name")
            .setValue(newName)
    }
    private fun changeEmail(){
        val newEmail = editAccountBinding.etEmailChangeAccount.text.toString()


        user!!.updateEmail(newEmail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    ref.child("person").child("email")
                        .setValue(newEmail)
                    Log.d(TAG, "User email address updated.")

                } else {
                    Log.d(TAG, "error Email")
                }
            }
    }
    private fun changePassword(){
        val newPassword = editAccountBinding.etPasswordChangeAccount.text.toString()

        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Log.d(TAG, "User password updated.")
                    ref.child("person").child("password")
                        .setValue(newPassword)
                } else {
                    Log.d(TAG, "error")
                }
            }
    }
    private fun changeTelephone(){
        val newTelephone = editAccountBinding.etTelephoneChangeAccount.text.toString()

        ref.child("person").child("telephone")
            .setValue(newTelephone)
    }

    companion object{
        private const val TAG = "editAccount"
    }
}

