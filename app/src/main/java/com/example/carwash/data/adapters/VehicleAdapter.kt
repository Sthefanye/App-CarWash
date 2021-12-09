package com.example.carwash.data.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.carwash.R
import com.example.carwash.data.model.Vehicle
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.ui.fragments.MeusVeiculosFragment


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

        view.setOnLongClickListener {
            val alertDialog = AlertDialog.Builder(context).apply {
                setIcon(R.drawable.ic_delete)
                setTitle(R.string.title)
                setMessage(R.string.message)
                setPositiveButton(R.string.sim) { _, _ ->
                    Log.d(TAG, "CLICK SIM")
                    val ref  = VehicleRepository.databaseReference.child(VehicleRepository.authReference.uid.toString()).child("vehicles")
                    ref.child(vehicle?.placa.toString()).removeValue()
                    remove(vehicle)
                }
                setNegativeButton(R.string.nao) { _, _ ->
                    Log.d(TAG, "CLICK NÃO")
                }
                show()
            }
            false
        }
        return view
    }

    companion object {
        const val TAG = "VehicleAdaper"
    }
}