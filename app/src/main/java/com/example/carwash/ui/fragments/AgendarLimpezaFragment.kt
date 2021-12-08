package com.example.carwash.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.data.adapters.VehicleAdapter
import com.example.carwash.data.model.Agendamento
import com.example.carwash.data.model.Vehicle
import com.example.carwash.data.repositories.ServiceRepository
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.data.util.Util
import com.example.carwash.databinding.FragmentAgendarServicoBinding
import com.example.carwash.ui.viewmodels.CarWashViewModel
import com.google.firebase.database.ktx.getValue
import java.util.ArrayList

class AgendarLimpezaFragment : Fragment() {

    private lateinit var agendarLimpezaBinding: FragmentAgendarServicoBinding
    private val viewModel: CarWashViewModel by viewModels()
    lateinit var listVehicle: ArrayList<Vehicle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        agendarLimpezaBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_agendar_servico, container, false)

        listVehicle = ArrayList<Vehicle>()

        navigate()
        calendar()
        dropDownHorarios()
        dropDownPlaca()
        clickSubmitAgendamento()

        return agendarLimpezaBinding.root
    }

    private fun navigate(){
        agendarLimpezaBinding.btnVoltar.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_agendar_limpeza_to_home)
        }
    }

    private fun calendar() {
        viewModel.dataLiveData.observe(viewLifecycleOwner, {
            agendarLimpezaBinding.tvDate.text = it
        })
        agendarLimpezaBinding.calendarView.setOnDateChangeListener { _, ano, mes, dia ->
            val date = "$dia/${mes + 1}/$ano"
            viewModel.dataLiveData.postValue(date)
            Log.d(TAG, "Dia escolhido $date")
        }
    }

    fun dropDownHorarios() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.Horarios,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            agendarLimpezaBinding.spListHorarios.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.Serviços,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            agendarLimpezaBinding.spListServico.adapter = adapter
        }

    }

    fun dropDownPlaca(){
        val ref  = VehicleRepository.databaseReference.child(VehicleRepository?.authReference?.uid.toString()).child("vehicles")
        ref.get().addOnCompleteListener { task ->

            if(task.isSuccessful){
                val result = task.result?.children
                val list = java.util.ArrayList<String>()
                result?.forEach {
                    val car = it.getValue<Vehicle>()
                    car?.let { it1 -> listVehicle.add(it1) }
                    car?.let { it1 -> list.add( it1.placa) }
                }

                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, list)
                agendarLimpezaBinding.spListPlaca.adapter = adapter
            }else{
                Util.exibirToast(requireContext(), "Não foi possivel carregar placas")
            }

        }

    }

    private fun clickSubmitAgendamento(){
        agendarLimpezaBinding.btnConfirmAgendamento.setOnClickListener {
            val agendamento = Agendamento()
            agendamento.data = viewModel.dataLiveData.value.toString()
            agendamento.hour = agendarLimpezaBinding.spListHorarios.selectedItem.toString()
            agendamento.service = agendarLimpezaBinding.spListServico.selectedItem.toString()
            agendamento.placa = agendarLimpezaBinding.spListPlaca.selectedItem.toString()
            agendamento.status = "Em andamento"

            agendamento.vehicle =
                listVehicle.firstOrNull { vehicle -> vehicle.placa == agendamento.placa }

            ServiceRepository.addAgendamento(agendamento)

            findNavController().navigate(R.id.nav_frag_agendar_limpeza_to_home)


        }
    }

    companion object {
        private const val TAG = "agendarServico"
    }

}