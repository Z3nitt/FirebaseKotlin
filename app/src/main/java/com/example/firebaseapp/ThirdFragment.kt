package com.example.firebaseapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class ThirdFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = requireActivity().intent.extras
        val email = bundle?.getString("email") ?: ""
        val provider = bundle?.getString("provider") ?: ""

        setup(view, email, provider)
    }

    private fun setup(view: View, email: String, provider: String){
        val tvEmailregistro = view.findViewById<TextView>(R.id.tvEmailregistro)
        val tvprovider = view.findViewById<TextView>(R.id.tvprovider)
        val btnCerrar = view.findViewById<Button>(R.id.btnCerrar)

        tvEmailregistro.text = email
        tvprovider.text = provider

        btnCerrar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            requireActivity().onBackPressed()
        }
    }
}