package com.example.carwash.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carwash.R
import com.example.carwash.databinding.FragmentStatusBinding


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
        statusBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_status, container, false)
        navigateStatusToHome()
        return statusBinding.root
    }

    private fun navigateStatusToHome() {
        statusBinding.btnVoltar.setOnClickListener {
            findNavController().navigate(R.id.nav_status_to_home)
        }
    }
}