package com.example.carwash.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.carwash.R
import com.example.carwash.databinding.FragmentListaServicosBinding
import com.example.carwash.databinding.ListItemServicoBinding

class ListServicoActivity : AppCompatActivity() {

    private lateinit var binding : ListItemServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ListItemServicoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        val title = intent.getStringExtra("title")
        val descricao = intent.getStringExtra("descricao")
        val preco = intent.getStringExtra("preco")
        val tempo = intent.getStringExtra("tempo")
        val imageId = intent.getIntExtra("imageId", R.drawable.ic_logo_servico)

        binding.tvTitleServico.text = title
        binding.tvDescriptionServico.text = descricao
        binding.tvPrecoServico.text = preco
        binding.tvTempoServico.text = tempo
        binding.ivIconCar.setImageResource(imageId)
    }

}