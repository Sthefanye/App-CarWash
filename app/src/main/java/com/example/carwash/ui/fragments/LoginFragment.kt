package com.example.carwash.ui.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import com.example.carwash.util.Util
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(), View.OnClickListener {

    var auth: FirebaseAuth? = null

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
        createAccount()

        return loginBinding.root
    }

    private fun navigateToHome() {

        loginBinding.btnLoginChangeAccount.setOnClickListener(this)
        loginBinding.tvCreateAccount.setOnClickListener(this)
    }

    private fun createAccount(){
        loginBinding.tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_login_to_cadastrar_user)
        }
    }


    companion object {
        const val TAG = "LoginFragment"
    }

    override fun onClick(p0: View?) {
        auth = Firebase.auth

        when (p0?.id) {

            btnLoginChangeAccount.id -> {
                buttonLogin()
            }
        }
    }


    private fun buttonLogin() {

        val email = etEmailChangeAccount.text.toString()
        val password = etPasswordChangeAccount.text.toString()

        //trim remove os espaços do input
        if (!email.trim().equals("") && !password.trim().equals("")) {
            btnLoginChangeAccount.setOnClickListener {

                if(Util.statusInternet(requireContext())){
                    login(email,password)
                }else{
                    Util.exibirToast(requireContext(), "Sem conexão com a internet")
                }
            }
        } else {
            Util.exibirToast(requireContext(), "campo em branco")
        }

    }

    fun login(email:String,password:String){

        auth?.signInWithEmailAndPassword(email,password)?.addOnCompleteListener(){task ->

            if(task.isSuccessful){

                Util.exibirToast(requireContext(),"Sucesso ao logar")
                loginBinding.btnLoginChangeAccount.setOnClickListener {
                    findNavController().navigate(R.id.nav_frag_login_to_home)
                }

            }else{

                Util.exibirToast(requireContext(),"Usuário ou senha inválido")

            }

        }

    }
}

