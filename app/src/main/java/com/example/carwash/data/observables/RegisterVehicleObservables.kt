package com.example.carwash.data.observables

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.carwash.BR

object RegisterVehicleObservables : BaseObservable() {

    @Bindable
    var modelo : String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.modelo)
    }

    @Bindable
    var placa : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.placa)
        }

    @Bindable
    var ano : Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.ano)
        }

    @Bindable
    var cor : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.cor)
        }
}