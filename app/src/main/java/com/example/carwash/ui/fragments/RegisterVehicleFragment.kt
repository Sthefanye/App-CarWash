package com.example.carwash.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.carwash.R
import com.example.carwash.databinding.FragmentListaServicosBinding
import com.example.carwash.databinding.FragmentRegisterVehicleBinding

class RegisterVehicleFragment : Fragment(){
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
        return registerVeiculoBinding.root
    }

}