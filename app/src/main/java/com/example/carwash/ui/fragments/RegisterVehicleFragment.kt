package com.example.carwash.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.data.model.Vehicle
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.databinding.FragmentRegisterVehicleBinding
import com.example.carwash.util.Util



class RegisterVehicleFragment() : Fragment(){
    private lateinit var registerVeiculoBinding: FragmentRegisterVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerVeiculoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_vehicle, container, false)
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

            val modelo = registerVeiculoBinding.etModeloVeiculo.text.toString()
            val placa = registerVeiculoBinding.etPlacaVeiculo.text.toString()
            val ano = registerVeiculoBinding.etAnoVeiculo.text.toString()
            val cor = registerVeiculoBinding.etCorVeiculo.text.toString()

            VehicleRepository.add(Vehicle(modelo, cor, placa, ano))

            Util.exibirToast(requireContext(), "Veiculo adicionado com sucesso")

            findNavController().navigate(R.id.nav_frag_register_veiculos_to_meus_veiculos)
        }
    }

}