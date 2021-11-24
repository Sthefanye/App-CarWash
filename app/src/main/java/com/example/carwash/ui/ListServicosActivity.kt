package com.example.carwash.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carwash.R.drawable.ic_logo_servico
import com.example.carwash.data.ListServicos
import com.example.carwash.data.adapters.ServicosAdapter
import com.example.carwash.databinding.FragmentListaServicosBinding
import com.example.carwash.ui.fragments.ListServicoActivity

class ListServicosActivity : AppCompatActivity() {

    private lateinit var binding: FragmentListaServicosBinding
    private lateinit var servicoArrayList : ArrayList<ListServicos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentListaServicosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        forList()
    }


    fun forList(){
        val imageId = intArrayOf(
            ic_logo_servico,
            ic_logo_servico,
            ic_logo_servico,
            ic_logo_servico,
            ic_logo_servico,
            ic_logo_servico
        )

        val title = arrayOf(
            "Lavagem Rápida",
            "Lavagem Simples",
            "Lavagem Completa",
            "Lavagem Detalhada",
            "Lavagem a Seco",
            "Higienização + Lavagem"
        )

        val descricao = arrayOf(
            "Lavagem exterior, pneus, e limpeza simples no painel.",
            "Lavagem exterior e interior, pneus, limpeza painel e aspiração.",
            "Lavagem completa exterior, interior, porta malas, motor e aspiração",
            "Lavagem detalhada exterior, interior (tudo), aspiração, finalização em cera",
            "Lavagem a Seco exterior, aspiração interior, finalização em cera",
            "Higienização dos bancos e intetior + lavagem completa"
        )

        val preco = arrayOf(
            "R$20,00",
            "R$30,00",
            "R$50,00",
            "R$70,00",
            "R$80,00",
            "R$120,00"
        )

        val tempo = arrayOf(
            "10-20 min",
            "20-30 min",
            "30-40 min",
            "40-80 min",
            "40-50 min",
            "3-5hrs"
        )

        servicoArrayList = ArrayList()

        for ( i in title.indices){
            val servico = ListServicos(title[i], descricao[i], preco[i],tempo[i], imageId[i])
            servicoArrayList.add(servico)
        }

        binding.lvServicos.isClickable = true
        binding.lvServicos.adapter = ServicosAdapter(this, servicoArrayList)
        binding.lvServicos.setOnItemClickListener { parent, view, position, id ->

            val title = title[position]
            val descricao = descricao[position]
            val preco = preco[position]
            val tempo = tempo[position]
            val imageId = imageId[position]

            val i = Intent(this, ListServicoActivity::class.java)
            i.putExtra("title", title)
            i.putExtra("descricao", descricao)
            i.putExtra("preco", preco)
            i.putExtra("tempo", tempo)
            i.putExtra("imageId", imageId)

            startActivity(i)
        }
    }

}