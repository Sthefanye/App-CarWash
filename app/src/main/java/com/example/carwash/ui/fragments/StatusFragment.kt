package com.example.carwash.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
        return statusBinding.root
    }

}