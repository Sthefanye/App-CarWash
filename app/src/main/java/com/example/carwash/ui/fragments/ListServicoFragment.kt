package com.example.carwash.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.carwash.R
import com.example.carwash.databinding.FragmentListaServicosBinding

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
        return listServicosBinding.root
    }

}