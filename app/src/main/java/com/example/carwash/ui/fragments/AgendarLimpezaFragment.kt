package com.example.carwash.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.carwash.R
import com.example.carwash.databinding.FragmentAgendarServicoBinding

class AgendarLimpezaFragment : Fragment() {

    private lateinit var agendarServicoBinding: FragmentAgendarServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        agendarServicoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_agendar_servico, container, false)
        return agendarServicoBinding.root
    }
}