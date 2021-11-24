package com.example.carwash.data.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.carwash.R
import com.example.carwash.data.ListServicos
import org.w3c.dom.Text

class ServicosAdapter(private val context: Activity, private val arrayList: ArrayList<ListServicos>) :
    ArrayAdapter<ListServicos>(context, R.layout.list_item_servico, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item_servico, null)

        val imageView : ImageView = view.findViewById(R.id.ivIconCar)
        val titleServico: TextView = view.findViewById(R.id.tvTitle)
        val descricaoServico: TextView = view.findViewById(R.id.tvDescriptionServico)
        val precoServico: TextView = view.findViewById(R.id.tvPrecoServico)
        val tempoServico: TextView = view.findViewById(R.id.tvTempoServico)

        imageView.setImageResource(arrayList[position].imageId)
        titleServico.text = arrayList[position].title
        descricaoServico.text = arrayList[position].descricao
        precoServico.text = arrayList[position].preco
        tempoServico.text = arrayList[position].tempo

        return view
    }
}