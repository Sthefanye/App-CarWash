package com.example.carwash.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarWashViewModel : ViewModel() {


    //Agendar Servico Fragment

    val dataLiveData : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}