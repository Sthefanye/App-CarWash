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
import com.example.carwash.data.repositories.PersonRepository
import com.example.carwash.databinding.FragmentHomeBinding
import com.example.carwash.data.util.Util
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private val user = Firebase.auth.currentUser
    private val databaseRef = Firebase.database.reference



    private val name3 = user?.displayName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        navigateHomeToConfiguracoes()
        navigateHomeToMeusVeiculos()
        navigateHomeToStatus()
        navigateHomeToAgendarLimpeza()
        navigateLogout()
        navigateToListServicos()
        loadDataUser()

        return homeBinding.root
    }

    private fun navigateHomeToMeusVeiculos() {
        homeBinding.icCadastrarVeiculo.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_home_to_meus_veiculos)
        }
    }

    private fun navigateHomeToAgendarLimpeza(){
        homeBinding.icAgendarLimpeza.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_home_to_agendarLimpeza)
        }
    }

    private fun navigateHomeToStatus() {
        homeBinding.icStatus.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_home_to_status)
        }
    }

    private fun navigateHomeToConfiguracoes() {
        homeBinding.icConfiguracoes.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_home_to_edit_account)
        }
    }
    private fun navigateLogout() {
        homeBinding.btnSair.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_home_to_login)
        }
    }

    private fun navigateToListServicos(){
        homeBinding.btnSair.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_home_to_lista_servicos)
        }
    }



    private fun loadDataUser() {
        PersonRepository.databaseReference.child(PersonRepository.authReference.uid.toString()).get().addOnCompleteListener{ task ->
            if(task.isSuccessful){
                val person = task.result?.child("person")?.getValue<Person>()
                Log.d("HOME", person?.name.toString())
                homeBinding.tvNomeUsuarioHome.setText("Olá, ${person?.name}")
            }
            homeBinding.tvNomeUsuario.text =  "Olá, Convidado"
        }

    }
}