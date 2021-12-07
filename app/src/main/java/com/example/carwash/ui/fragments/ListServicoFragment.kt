package com.example.carwash.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.data.ListServicos
import com.example.carwash.data.adapters.ServicosAdapter
import com.example.carwash.data.adapters.VehicleAdapter
import com.example.carwash.data.model.Service
import com.example.carwash.data.model.Vehicle
import com.example.carwash.data.repositories.ServiceRepository
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.databinding.FragmentListaServicosBinding
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.ArrayList

class ListServicoFragment : Fragment() {

    private lateinit var listServicosBinding: FragmentListaServicosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listServicosBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lista_servicos, container, false)
        GlobalScope.async {loadService()}
        return listServicosBinding.root
    }

    suspend fun loadService() {
        val ref  = ServiceRepository.dataServiceReference
        ref.get().addOnCompleteListener { task ->

            val result = task.result?.children
            val list = ArrayList<Service>()
            result?.forEach {
                val servico = it.getValue<Service>()

                Log.d("Service", it.value.toString())
                servico?.let { it1 -> list.add(it1) }
            }

            //val adapterService = ServicosAdapter(requireActivity(), list)
            //listServicosBinding.lvServicos.adapter = adapterService
        }
    }

}