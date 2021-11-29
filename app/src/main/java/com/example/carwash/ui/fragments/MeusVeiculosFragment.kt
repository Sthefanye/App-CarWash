package com.example.carwash.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.carwash.R
import com.example.carwash.databinding.FragmentLoginBinding
import com.example.carwash.databinding.FragmentMeusVeiculosBinding

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
        return meusVeiculosBinding.root
    }

}