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
import com.example.carwash.databinding.FragmentAgendarServicoBinding
import com.example.carwash.ui.viewmodels.CarWashViewModel

class AgendarLimpezaFragment : Fragment() {

    private lateinit var agendarLimpezaBinding: FragmentAgendarServicoBinding
    private val viewModel: CarWashViewModel by viewModels()

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
        calendar()
        dropDownHorarios()

        return agendarLimpezaBinding.root
    }

    private fun navigate(){
        agendarLimpezaBinding.btnVoltar.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_agendar_limpeza_to_list_servicos)
        }
    }

    private fun calendar() {
        viewModel.dataLiveData.observe(viewLifecycleOwner, {
            agendarLimpezaBinding.tvDate.text = it
        })
        agendarLimpezaBinding.calendarView.setOnDateChangeListener { _, ano, mes, dia ->
            val date = "$dia/${mes + 1}/$ano"
            viewModel.dataLiveData.postValue("Data: $date")
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
    }


    companion object {
        private const val TAG = "agendarServico"
    }

}