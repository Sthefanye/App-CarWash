package com.example.carwash.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.databinding.FragmentEditAccountBinding

class EditAccountFragment : Fragment(){
    private lateinit var editAccountBinding: FragmentEditAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editAccountBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_edit_account, container, false)
        navigate()
        return editAccountBinding.root
    }

    fun navigate(){
        editAccountBinding.btnSaveAccount.setOnClickListener{
            findNavController().navigate(R.id.nav_edit_account_to_home)
        }
        editAccountBinding.btnVoltar.setOnClickListener{
            findNavController().navigate(R.id.nav_edit_account_to_home)
        }

    }

}