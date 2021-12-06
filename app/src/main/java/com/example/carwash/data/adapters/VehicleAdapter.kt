package com.example.carwash.data.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.carwash.R
import com.example.carwash.data.model.Vehicle
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast


class VehicleAdapter(context: Context, arrayList: ArrayList<Vehicle>) :
    ArrayAdapter<Vehicle>(context, R.layout.list_item_meus_veiculos, arrayList) {

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vehicle: Vehicle? = getItem(position)

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item_meus_veiculos, null)

        val tvCor: TextView = view.findViewById(R.id.tvCorCarMeusVeiculos) as TextView
        val tvAno: TextView = view.findViewById(R.id.tvAnoCarMeusVeiculos) as TextView
        val tvModel: TextView = view.findViewById(R.id.tvModelCarMeusVeiculos) as TextView
        val tvPlaca: TextView = view.findViewById(R.id.tvPlacaCarMeusVeiculos) as TextView

        tvAno.text = "Ano: ${vehicle?.ano}"
        tvModel.text = "Modelo: ${vehicle?.modelo}"
        tvCor.text = "Cor: ${vehicle?.cor}"
        tvPlaca.text = "Placa: ${vehicle?.placa}"

        view.setOnLongClickListener ( View.OnLongClickListener {
            Toast.makeText(context, " click Longo", Toast.LENGTH_SHORT).show()
            false
        })

        view.setOnClickListener {
            Toast.makeText(context, " click curto", Toast.LENGTH_SHORT).show()

        }

        return view
    }
}