package com.example.firebaseapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }


    private fun setup(email: String, provider: String){
        title = "Inicio"
        var tvEmailregistro = findViewById<TextView>(R.id.tvEmailregistro)
        var tvprovider = findViewById<TextView>(R.id.tvprovider)
        var btnCerrar = findViewById<Button>(R.id.btnCerrar)


        tvEmailregistro.text = email
        tvprovider.text = provider

        btnCerrar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}