package com.example.carwash.data.observables

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.carwash.BR

class AgendarServicoObservable : BaseObservable(){

    @Bindable
    var dateObservable : String = ""
        set(value) {
        field = value
        notifyPropertyChanged(BR.dateObservable)
    }
}