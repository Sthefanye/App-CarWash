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
import com.example.carwash.data.adapters.StatusAdapter
import com.example.carwash.data.model.Agendamento
import com.example.carwash.data.model.Vehicle
import com.example.carwash.data.repositories.ServiceRepository
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.databinding.FragmentStatusBinding
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await


class StatusFragment : Fragment() {
    private lateinit var statusBinding: FragmentStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        statusBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_status, container, false)
        navigateStatusToHome()
        GlobalScope.async { stausLoad() }
        return statusBinding.root
    }

    private suspend fun stausLoad() {
        val list = ArrayList<String>()

        VehicleRepository.databaseReference.child(VehicleRepository?.authReference?.uid.toString())
            .child("vehicles").get().addOnCompleteListener { task ->

                val result = task.result?.children

                result?.forEach {
                    val car = it.getValue<Vehicle>()
                    Log.d("Vehicle", it.value.toString())
                    car?.let { it1 -> list.add(it1.placa) }
                }
            }.await()


        VehicleRepository.dataAgendamentoReference.get().addOnCompleteListener { task ->
            val listAgen = ArrayList<Agendamento>()

            list.forEach {
                task.result?.child(it)?.getValue<Agendamento>().let {
                    it?.let { it1 -> listAgen.add(it1) }
                }
            }

            statusBinding.lvMeusStatuss.adapter = StatusAdapter(requireContext(), listAgen)
            //statusBinding.lvMeusStatuss.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listAgen)
        }.await()

    }

    private fun navigateStatusToHome() {
        statusBinding.btnVoltar.setOnClickListener {
            findNavController().navigate(R.id.nav_status_to_home)
        }
    }
}