package com.example.carwash.data.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.carwash.R
import com.example.carwash.data.model.Agendamento
import com.example.carwash.data.model.Person
import com.example.carwash.data.model.Vehicle
import com.example.carwash.data.repositories.VehicleRepository
import com.example.carwash.ui.fragments.MeusVeiculosFragment

class StatusAdapter(context: Context, arrayList: ArrayList<Agendamento>) :
    ArrayAdapter<Agendamento>(context, R.layout.list_item_status, arrayList) {

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val agendamento: Agendamento? = getItem(position)

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item_status, null)

        val tvVehicleService: TextView = view.findViewById(R.id.tvVehicleService) as TextView
        val tvStatusService: TextView = view.findViewById(R.id.tvStatusService) as TextView
        val tvCorCarStatusService: TextView = view.findViewById(R.id.tvCorCarStatusService) as TextView
        val tvPlacaCarStatusService: TextView = view.findViewById(R.id.tvPlacaCarStatusService) as TextView
        val tvAnoCarStatusService: TextView = view.findViewById(R.id.tvAnoCarStatusService) as TextView
        val tvModelCarStatusService: TextView = view.findViewById(R.id.tvModelCarStatusService) as TextView

        tvVehicleService.text = "${agendamento?.service}"
        tvStatusService.text = "Status: ${agendamento?.status}"
        tvCorCarStatusService.text = "Cor: ${agendamento?.vehicle?.cor}"
        tvPlacaCarStatusService.text = "Placa: ${agendamento?.vehicle?.placa}"
        tvAnoCarStatusService.text = "Ano: ${agendamento?.vehicle?.ano}"
        tvModelCarStatusService.text = "Modelo: ${agendamento?.vehicle?.modelo}"

        view.setOnLongClickListener {
            val alertDialog = AlertDialog.Builder(context).apply {
                setIcon(R.drawable.ic_delete)
                setTitle(R.string.title)
                setMessage(R.string.message_status)
                setPositiveButton(R.string.sim) { _, _ ->
                    Log.d(VehicleAdapter.TAG, "CLICK SIM")
                    val ref  = VehicleRepository.dataAgendamentoReference.child(agendamento?.placa.toString())
                    ref.removeValue()
                    remove(agendamento)
                }
                setNegativeButton(R.string.nao) { _, _ ->
                    Log.d(VehicleAdapter.TAG, "CLICK NÃO")
                }
                show()
            }
            false
        }

        return view
    }
}