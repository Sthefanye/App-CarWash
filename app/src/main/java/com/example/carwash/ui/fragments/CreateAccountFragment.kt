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
import com.example.carwash.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.example.carwash.data.model.Person
import com.example.carwash.model.CreateAccountData
import com.example.carwash.util.Util

class CreateAccountFragment : Fragment() {
    private lateinit var createAccountBinding: FragmentCreateAccountBinding
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference
    private val user = FirebaseAuth.getInstance()
    private var auth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createAccountBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_account,
            container, false
        )
        btnCreateAccount()
        navigateToLogin()
        return createAccountBinding.root
    }

    private fun navigateToLogin() {
        createAccountBinding.tvLoginCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_cadastrar_login_to_login)
        }
    }


    private fun btnCreateAccount() {
        createAccountBinding.btnCreateChangeAccount.setOnClickListener {
            val data = CreateAccountData()
            val dataUser = Person(
                userId = System.currentTimeMillis().toInt(),
                userEmail = createAccountBinding.etEmailChangeAccount.text.toString(),
                userName = createAccountBinding.etNameChangeAccount.text.toString(),
                userNumber = createAccountBinding.etTelephoneChangeAccount.text.toString(),
                userPassword = createAccountBinding.etPasswordChangeAccount.text.toString()
            )
            if (dataUser.userEmail.trim() != "" &&
                dataUser.userPassword.trim() != "" &&
                dataUser.userNumber.trim() != "" &&
                dataUser.userName.trim() != ""
            ) {

                databaseReference.child("Users").child(dataUser.userId.toString())
                    .child("email").setValue(dataUser.userEmail)

                databaseReference.child("Users").child(dataUser.userId.toString())
                    .child("name").setValue(dataUser.userName)

                databaseReference.child("Users").child(dataUser.userId.toString())
                    .child("telephone").setValue(dataUser.userNumber)

                databaseReference.child("Users").child(dataUser.userId.toString())
                    .child("password").setValue(dataUser.userPassword)

                user.createUserWithEmailAndPassword(
                    dataUser.userEmail,
                    dataUser.userPassword
                ).addOnCompleteListener(requireActivity())
                { task ->
                    if (task.isSuccessful) {

                        findNavController().navigate(R.id.nav_frag_cadastrar_login_to_home)
                        Util.exibirToast(
                            requireContext(), "Conta criada " +
                                    "com sucesso"
                        )

                        Log.d("logcat", "createUserWithEmail:success")
                        val user = auth?.currentUser
                    } else {
                        val error = task.exception.toString()
                        errorsFirebase(error)
                        Log.d(TAG, task.exception.toString())
                    }
                }
            } else {
                Util.exibirToast(requireContext(), "Preencher campo vazio")
            }
        }
    }

    private fun errorsFirebase(error: String) {

        when {
            error.contains("The email address is badly formatted.") -> {
                Util.exibirToast(requireContext(), "Inserir e-mail válido")
            }
            error.contains("The given password is invalid") -> {
                Util.exibirToast(
                    requireContext(), "A senha deve conter no mínimo" +
                            " 6 caracteces"
                )
            }
            error.contains(
                "The password is invalid or the user does not have" +
                        " a password."
            ) -> {
                Util.exibirToast(requireContext(), "Este e-mail não é válido")
            }
            error.contains("The email address is already in use by another account.") -> {
                Util.exibirToast(requireContext(), "Email já utilizado por outra pessoa")
            }
            else -> {
                Util.exibirToast(requireContext(), "Ocorreu um erro inesperado")
            }
        }
    }

    companion object {
        private const val TAG = "createAccount"
    }
}