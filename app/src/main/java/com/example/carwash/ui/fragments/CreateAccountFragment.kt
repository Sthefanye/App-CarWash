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
import com.example.carwash.ui.fragments.LoginFragment.Companion.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_create_account.*
import com.example.carwash.data.model.Person
import com.example.carwash.model.CreateAccountData
import com.example.carwash.util.DialogProgress
import com.example.carwash.util.Util
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_create_account.etEmailChangeAccount
import kotlinx.android.synthetic.main.fragment_create_account.etPasswordChangeAccount
import kotlinx.android.synthetic.main.fragment_login.*

class CreateAccountFragment : Fragment() {
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.reference

    val usuario = FirebaseAuth.getInstance()
    private var auth: FirebaseAuth? = null


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
        btnCreateAccount()
        navigateToLogin()
        return createAccountBinding.root
    }

    private fun navigateToLogin(){
        createAccountBinding.tvLoginCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_cadastrar_login_to_home)
        }
    }


    private fun btnCreateAccount(){
        createAccountBinding.btnCreateChangeAccount.setOnClickListener{
            val data = CreateAccountData()
            val dadosUsuario = Person(
                userEmail = etEmailChangeAccount.text.toString(),
                userName = etNameChangeAccount.text.toString(),
                userNumber = etTelephoneChangeAccount.text.toString(),
                userPassword = etPasswordChangeAccount.text.toString()
            )
            if (!dadosUsuario.userEmail.trim().equals("") &&
                !dadosUsuario.userPassword.trim().equals("") &&
                !dadosUsuario.userNumber.trim().equals("") &&
                !dadosUsuario.userName.trim().equals("")) {

                val userid = etEmailChangeAccount.id.toString()

                    databaseReference.child("Users").child(userid).child("email").setValue(dadosUsuario.userEmail)
                    databaseReference.child("Users").child(userid).child("name").setValue(dadosUsuario.userName)
                    databaseReference.child("Users").child(userid).child("telephone").setValue(dadosUsuario.userNumber)
                    databaseReference.child("Users").child(userid).child("password").setValue(dadosUsuario.userPassword)

                    usuario.createUserWithEmailAndPassword(dadosUsuario.userEmail, dadosUsuario.userPassword)
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                findNavController().navigate(R.id.nav_frag_cadastrar_login_to_home)
                                Util.exibirToast(requireContext(),"Conta criada com sucesso")

                                Log.d("logcat", "createUserWithEmail:success")
                                val user = auth?.currentUser
                            } else {
                                val erro = task.exception.toString()
                                errorsFirebase(erro)
                                Log.d("logcat",task.exception.toString())
                            }
                        }
            }else{
                Util.exibirToast(requireContext(),"Preencher campo vazio")
            }
        }
    }

    private fun errorsFirebase(erro:String){

        if(erro.contains("The email address is badly formatted." )){
            Util.exibirToast(requireContext(),"Inserir e-mail válido")
        }else if(erro.contains("The given password is invalid")){
            Util.exibirToast(requireContext(),"A senha deve conter no mínimo 6 caracteces")
        }else if(erro.contains("The password is invalid or the user does not have a password.")) {
            Util.exibirToast(requireContext(), "Este e-mail não é válido")
        }else if(erro.contains("The email address is already in use by another account.")) {
            Util.exibirToast(requireContext(), "Email já utilizado por outra pessoa")
        }else{
            Util.exibirToast(requireContext(), "Ocorreu um erro inesperado")
        }
    }
}