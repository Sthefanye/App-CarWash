package com.example.carwash.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.carwash.BR

class CreateAccountData : BaseObservable() {
    @Bindable
    var name : String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.name)
    }

    @Bindable
    var email : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }
    @Bindable
    var password : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @Bindable
    var telephone : Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.telephone)
        }
}