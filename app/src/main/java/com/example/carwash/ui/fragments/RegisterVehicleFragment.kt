package com.example.carwash.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.data.model.Vehicle
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.databinding.FragmentRegisterVehicleBinding
import com.example.carwash.data.util.Util


class RegisterVehicleFragment() : Fragment() {
    private lateinit var registerVeiculoBinding: FragmentRegisterVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerVeiculoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register_vehicle, container, false)
        navigateToHome()
        navigateToMeusVeiculos()

        return registerVeiculoBinding.root
    }


    private fun navigateToHome() {
        registerVeiculoBinding.btnVoltarHome.setOnClickListener {
            findNavController().navigate(R.id.nav_frag_register_veiculos_to_meus_veiculos)
        }
    }

    private fun navigateToMeusVeiculos() {
        registerVeiculoBinding.btnConfirmRegisterVeiculo.setOnClickListener {

            val modelo = registerVeiculoBinding.etModeloVeiculo
            val placa = registerVeiculoBinding.etPlacaVeiculo
            val ano = registerVeiculoBinding.etAnoVeiculo
            val cor = registerVeiculoBinding.etCorVeiculo

            if (modelo.text.isNullOrEmpty()) {
                modelo.error = "Campo Obrigatorio"
                return@setOnClickListener
            }
            if (placa.text.isNullOrEmpty()) {
                placa.error = "Campo Obrigatorio"
                return@setOnClickListener
            }
            if (ano.text.isNullOrEmpty()) {
                ano.error = "Campo Obrigatorio"
                return@setOnClickListener
            }
            if (cor.text.isNullOrEmpty()) {
                cor.error = "Campo Obrigatorio"
                return@setOnClickListener
            }


            VehicleRepository.add(Vehicle(modelo.text.toString(), cor.text.toString(), placa.text.toString(), ano.text.toString()))

            Util.exibirToast(requireContext(), "Veiculo adicionado com sucesso")

            findNavController().navigate(R.id.nav_frag_register_veiculos_to_meus_veiculos)
        }
    }


}