package com.example.carwash.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.data.adapters.VehicleAdapter
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.data.util.Util
import com.example.carwash.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*

class ForgotPasswordFragment : Fragment(){

    private lateinit var forgotPasswordBinding: FragmentForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        forgotPasswordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false)
        resetAccount()
        navigateToLogin()

        return forgotPasswordBinding.root
    }

    private fun navigateToLogin() {
        forgotPasswordBinding.bntVoltar.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_forgot_pass_to_login)
        }
    }

    private fun resetAccount() {
        forgotPasswordBinding.btnForgotPassword.setOnClickListener {

            val emailAddress = forgotPasswordBinding.etEmailForgotPassword

            if(!emailAddress.text.toString().isNullOrEmpty()){
                Firebase.auth.sendPasswordResetEmail(emailAddress.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Util.exibirToast(requireContext(), "E-mail enviado. \n Por favor verifique seu e-mail \n ")
                            findNavController().navigate(R.id.nav_frag_forgot_pass_to_login)
                            val alertDialog = AlertDialog.Builder(context).apply {
                                setIcon(R.drawable.ic_warning)
                                setTitle(R.string.title_email)
                                setMessage(R.string.message_email)
                                setPositiveButton(R.string.ok) { _, _ ->
                                    Log.d(VehicleAdapter.TAG, "CLICK OK")
                                }
                                show()
                            }
                        }else{
                            val error = task.exception.toString()
                            errorsFirebase(error)
                            Log.d(TAG, task.exception.toString())
                        }
                    }
            }else {
                emailAddress.error = "Por favor digite seu e-mail"
            }

        }
    }

    private fun errorsFirebase(error: String) {
        when {
            error.contains("There is no user record corresponding to this identifier. The user may have been deleted.") -> {
                Util.exibirToast(requireContext(), "E-mail não cadastrado")
            }
            error.contains("The email address is badly formatted") -> {
                Util.exibirToast(requireContext(), "Este e-mail não é válido")
            }
            else -> {
                Util.exibirToast(requireContext(), "Ocorreu um erro inesperado")
            }
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}