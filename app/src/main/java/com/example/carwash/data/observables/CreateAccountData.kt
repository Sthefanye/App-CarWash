package com.example.carwash.model

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.carwash.BR


open class CreateAccountData : BaseObservable() {
}

class Users : CreateAccountData() {
    var users: Id = Id()
}

class Id : CreateAccountData() {
    @Bindable
    var id: MutableList<DataUser> = mutableListOf()
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }
}

class DataUser() : CreateAccountData() {

    @Bindable
    var email: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @Bindable
    var telephone: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.telephone)
        }

}