package com.example.carwash.data.observables

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.carwash.BR

class LoginData : BaseObservable() {

    @Bindable
    var email : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @Bindable
    var senha : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.senha)

        }

}