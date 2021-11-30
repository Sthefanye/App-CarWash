package com.example.carwash.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.databinding.FragmentHomeBinding

class HomeFragment : Fragment(){
    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        navigate()
        return homeBinding.root
    }

    fun navigate(){
        homeBinding.icCadastrarVeiculo.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_home_to_meus_veiculos)
        }
        homeBinding.icAgendarLimpeza.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_home_to_agendarLimpeza)
        }
        homeBinding.icStatus.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_home_to_status)
        }
        homeBinding.icConfiguracoes.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_home_to_edit_account)
        }
    }
}