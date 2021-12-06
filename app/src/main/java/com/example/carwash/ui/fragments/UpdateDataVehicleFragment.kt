package com.example.carwash.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.databinding.FragmentUpdateDadosVehicleBinding

class UpdateDataVehicleFragment : Fragment() {

    private lateinit var updateDadosVehicleBinding: FragmentUpdateDadosVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateDadosVehicleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_vehicle, container, false)
        navigateToHome()

        return updateDadosVehicleBinding.root
    }


    private fun navigateToHome() {
        updateDadosVehicleBinding.btnConfirmUpdateDataVeiculo.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_update_veiculos_to_meus_veiculos)
        }
    }
}