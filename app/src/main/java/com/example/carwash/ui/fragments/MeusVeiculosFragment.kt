package com.example.carwash.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.data.adapters.VehicleAdapter
import com.example.carwash.data.model.Vehicle
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.databinding.FragmentLoginBinding
import com.example.carwash.databinding.FragmentMeusVeiculosBinding
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.async
import java.util.ArrayList

import kotlin.coroutines.CoroutineContext

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
        //loadListVehicle()
        GlobalScope.async {loadListVehicle()  }

        return meusVeiculosBinding.root
    }

    @DelicateCoroutinesApi
    suspend fun loadListVehicle() {
        val ref  = VehicleRepository.databaseReference.child(VehicleRepository?.authReference?.uid.toString()).child("vehicles")
        ref.get().addOnCompleteListener { task ->

            val result = task.result?.children
            val list = ArrayList<Vehicle>()
            result?.forEach {
                val car = it.getValue<Vehicle>()

                Log.d("Vehicle", it.value.toString())
                car?.let { it1 -> list.add( it1) }
            }


            val adapterVehicle = VehicleAdapter(requireContext(), list)

            //val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
            meusVeiculosBinding.lvMeusVeiculos.adapter = adapterVehicle
        }


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