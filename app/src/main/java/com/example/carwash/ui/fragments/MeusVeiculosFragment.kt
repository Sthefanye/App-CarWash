package com.example.carwash.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.databinding.FragmentLoginBinding
import com.example.carwash.databinding.FragmentMeusVeiculosBinding

class MeusVeiculosFragment : Fragment() {

    private lateinit var meusVeiculosBinding: FragmentMeusVeiculosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        meusVeiculosBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_meus_veiculos, container, false)
        navigateAgendarLimpezaToHome()
        navigateCadastrarVeiculo()


        return meusVeiculosBinding.root
    }

    private fun navigateAgendarLimpezaToHome(){
        meusVeiculosBinding.btnMeusVeiculosToHome.setOnClickListener {
            findNavController().navigate(R.id.nav_meus_veiculos_to_home)
        }
    }

    private fun navigateCadastrarVeiculo(){
        meusVeiculosBinding.btnAddVehicle.setOnClickListener {
            findNavController().navigate(R.id.nav_meus_veiculos_to_register_veiculos)
        }
    }

}