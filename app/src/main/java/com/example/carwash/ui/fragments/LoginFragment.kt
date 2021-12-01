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

class LoginFragment : Fragment(), View.OnClickListener {

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

        return loginBinding.root
    }

    private fun navigateToHome() {

        loginBinding.btnLoginChangeAccount.setOnClickListener(this)
        loginBinding.tvCreateAccount.setOnClickListener(this)
    }


    companion object {
        const val TAG = "LoginFragment"
    }

    override fun onClick(p0: View?) {
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

                loginBinding.btnLoginChangeAccount.setOnClickListener {
                    findNavController().navigate(R.id.nav_frag_login_to_home)
                }
                Util.exibirToast(requireContext(), "Login com sucesso")
            }


        } else {
            Util.exibirToast(requireContext(), "campo em branco")
        }

    }
}