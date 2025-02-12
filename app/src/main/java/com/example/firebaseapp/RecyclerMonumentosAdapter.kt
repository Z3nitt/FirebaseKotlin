package com.example.firebaseapp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ListaMonumentos

class RecyclerMonumentosAdapter(
    private val monumentos: List <ListaMonumentos>,
    private val onItemClick: (Int) -> Unit //Maneja el click
    ) : RecyclerView.Adapter<RecyclerMonumentosAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_monumentos, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val monumento = monumentos[position]
        holder.imagenPortada.setImageResource(monumento.idImagen)
        holder.nombre.text = monumento.nombre
        holder.descripcion.text = monumento.descripcion

        holder.itemView.setOnClickListener {
            onItemClick(position)//Llama a la func de arriba
        }
        setAnimation(holder.itemView)
    }

    override fun getItemCount(): Int = monumentos.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imagenPortada: ImageView = v.findViewById(R.id.imagenPortada)
        val nombre: TextView = v.findViewById(R.id.textoSuperior)
        val descripcion: TextView = v.findViewById(R.id.textoInferior)

        init {
            nombre.setTextColor(Color.BLACK)
            descripcion.setTextColor(Color.BLACK)
        }
    }

    private fun setAnimation(view: View) {
        val animacion = ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat("translationX", 300f, 0f),
            PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
        )
        animacion.duration = 500
        animacion.start()
    }
}