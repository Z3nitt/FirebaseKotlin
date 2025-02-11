package com.example.firebaseapp

import ListaRecompensas
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

class AdapterRecycler(private val recompensas: List<ListaRecompensas>) :
    RecyclerView.Adapter<AdapterRecycler.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.recompensas, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val recompensa = recompensas[position]
        holder.imagenPortada.setImageResource(recompensa.idImagen)
        holder.textoSuperior.text = recompensa.textoSuperior
        holder.textoInferior.text = recompensa.textoInferior

        holder.itemView.setOnClickListener { v ->
            Toast.makeText(
                v.context,
                "Nombre de la recompensa: ${recompensas[position].textoSuperior}",
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.itemView.setOnLongClickListener { v ->
            Toast.makeText(v.context, "Click mantenido", Toast.LENGTH_SHORT).show()
            true
        }

        setAnimation(holder.itemView)
    }

    override fun getItemCount(): Int = recompensas.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal val imagenPortada: ImageView = v.findViewById(R.id.imagenPortada)
        val textoSuperior: TextView = v.findViewById(R.id.textoSuperior)
        val textoInferior: TextView = v.findViewById(R.id.textoInferior)

        init {
            textoSuperior.setTextColor(Color.BLACK)
            textoInferior.setTextColor(Color.BLACK)
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
