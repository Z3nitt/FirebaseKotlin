package com.example.firebaseapp

import ListaRecompensas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstFragment : Fragment() {

    private lateinit var recompensas: ArrayList<ListaRecompensas>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        // Obtener el RecyclerView desde el layout del fragmento
        val recyclerViewRecompensas = view.findViewById<RecyclerView>(R.id.recyclerViewRecompensas)

        // Inicializar la lista de recompensas correctamente
        recompensas = ListaRecompensas.obtenerRecompensas()

        // Configurar el RecyclerView
        recyclerViewRecompensas.layoutManager = LinearLayoutManager(context)
        recyclerViewRecompensas.adapter = AdapterRecycler(recompensas)

        return view
    }
}
