package com.example.carwash.data.observables

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.carwash.BR

class LoginFragmentObservables : BaseObservable() {

    @Bindable
    var emailUser : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.emailUser)
        }

    @Bindable
    var senhaUser : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.senhaUser)
        }
}