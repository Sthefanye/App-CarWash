package com.example.carwash.data.observables

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.carwash.BR

object LoginFragmentObservables : BaseObservable() {

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