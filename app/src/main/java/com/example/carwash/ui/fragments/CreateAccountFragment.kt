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

import com.example.carwash.databinding.FragmentCreateAccountBinding
import com.example.carwash.data.repositories.PersonRepository
import com.example.carwash.data.util.DialogProgress
import com.example.carwash.model.CreateAccountData
import com.example.carwash.data.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase



class CreateAccountFragment : Fragment() {
    private lateinit var createAccountBinding: FragmentCreateAccountBinding
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    val dialogProgress = DialogProgress()
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
            dialogProgress.show(parentFragmentManager, "1")

            val data = CreateAccountData()
            val dataUser = Person(
                //userId = System.currentTimeMillis().toInt(),
                email = createAccountBinding.etEmailChangeAccount.text.toString(),
                name = createAccountBinding.etNameChangeAccount.text.toString(),
                telephone = createAccountBinding.etTelephoneChangeAccount.text.toString(),
                password = createAccountBinding.etPasswordChangeAccount.text.toString()
            )
            if (dataUser.email.trim() != "" &&
                dataUser.password.trim() != "" &&
                dataUser.telephone.trim() != "" &&
                dataUser.name.trim() != ""
            ) {

                user.createUserWithEmailAndPassword(
                    dataUser.email,
                    dataUser.password
                ).addOnCompleteListener(requireActivity())
                { task ->
                    if (task.isSuccessful) {

                        Util.exibirToast(
                            requireContext(), "Conta criada " +
                                    "com sucesso"
                        )

                        findNavController().navigate(R.id.nav_frag_cadastrar_login_to_home)

                        task.result?.user?.let {

                            dataUser.id = it.uid
                            PersonRepository.add(dataUser)
                        }


                    } else {
                        val error = task.exception.toString()
                        errorsFirebase(error)
                        Log.d(TAG, task.exception.toString())
                    }
                    dialogProgress.dismiss()
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