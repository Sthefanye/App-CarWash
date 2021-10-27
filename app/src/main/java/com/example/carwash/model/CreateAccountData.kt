package com.example.carwash.model

import androidx.databinding.BaseObservable

class CreateAccountData : BaseObservable() {
    var name : String = ""
    var email : String = ""
    var password : String = ""
    var telephone : Int = 0
}