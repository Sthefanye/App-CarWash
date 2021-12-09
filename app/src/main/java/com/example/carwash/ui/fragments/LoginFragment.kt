package com.example.carwash.ui.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.data.repositories.PersonRepository
import com.example.carwash.databinding.FragmentLoginBinding
import com.example.carwash.data.util.DialogProgress
import kotlinx.android.synthetic.main.fragment_login.*
import com.example.carwash.data.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(), View.OnClickListener {
    private var auth: FirebaseAuth? = null
    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        navigateToHome()
        navigateToForgotPassword()

        return loginBinding.root
    }


    private fun navigateToHome() {
        loginBinding.btnLoginChangeAccount.setOnClickListener(this)

        loginBinding.tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_login_to_cadastrar_user)
        }
    }

    private fun navigateToForgotPassword(){
        loginBinding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.nav_login_to_frag_forgot_pass)
        }
    }

    override fun onClick(p0: View?) {
        auth = Firebase.auth
        when (p0?.id) {

            btnLoginChangeAccount.id -> {
                buttonLogin()
            }
        }
    }


    @SuppressLint("ResourceAsColor")
    private fun buttonLogin() {

        val email = etEmailChangeAccount.text.toString()
        val password = etPasswordChangeAccount.text.toString()

        //trim remove os espaços do input
        if (email.trim() != "" && password.trim() != "") {
                //  btnLoginChangeAccount.setBackgroundResource(R.color.white)
                if (Util.statusInternet(requireContext())) {
                    login(email, password)
                } else {
                    Util.exibirToast(requireContext(), "Sem conexão com a internet")
                }
        } else {
            Util.exibirToast(requireContext(), "Campo em branco")
        }

    }

    fun login(email: String, password: String) {
        val dialogProgress = DialogProgress()
        dialogProgress.show(parentFragmentManager, "1")

        auth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener { task ->
            dialogProgress.dismiss()
            if (task.isSuccessful) {
                findNavController().navigate(R.id.nav_frag_login_to_home)
                Util.exibirToast(requireContext(), "Sucesso ao logar")
            } else {
                //Util.exibirToast(requireContext(), "Usuário ou senha inválido ${task.exception.toString()}")
                val error = task.exception.toString()
                errorsFirebase(error)
                Log.d(TAG, task.exception.toString())

            }

        }

    }

    private fun errorsFirebase(error: String) {
        when {
            error.contains("There is no user record corresponding to this identifier. The user may have been deleted.") -> {
                Util.exibirToast(requireContext(), "E-mail não cadastrado")
            }
            error.contains("The password is invalid or the user does not have a password.") -> {
                Util.exibirToast(requireContext(), "Usuário ou Senha inválida")
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


