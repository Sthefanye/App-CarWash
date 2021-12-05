package com.example.carwash.data.observables

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.carwash.BR

class ServicesCatalogData : BaseObservable() {
    @Bindable
    var title : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @Bindable
    var description: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    @Bindable
    var price: Float = 0.0F
        set(value) {
            field = value
            notifyPropertyChanged(BR.price)
        }

    @Bindable
    var duration: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.price)
        }
}