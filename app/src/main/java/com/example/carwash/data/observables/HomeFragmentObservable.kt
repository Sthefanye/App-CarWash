package com.example.carwash.data.observables

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.carwash.BR

object HomeFragmentObservable : BaseObservable() {

    @Bindable
    var nameUser: String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.nameUser)
    }
}