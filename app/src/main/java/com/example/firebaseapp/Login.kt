package com.example.firebaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.example.firebaseapp.ProviderType

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setup()
    }

    private fun setup() {
        title = "Login"
        var btnSignUp = findViewById<Button>(R.id.btnSignUp)
        var btnLogin = findViewById<Button>(R.id.btnLogin)
        var etEmail = findViewById<EditText>(R.id.tvEmailregistro)
        var etPwd = findViewById<EditText>(R.id.tvPwdregistro)


        btnSignUp.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etPwd.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(etEmail.text.toString(), etPwd.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?: "", ProviderType.USUARIO)
                        } else {
                            showAlertPWD()
                        }
                    }
            }
        }

        btnLogin.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etPwd.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(etEmail.text.toString(), etPwd.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?: "", ProviderType.USUARIO)
                        } else {
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Usuario No Registrado")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlertPWD() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("La contraseña tiene que tener al menos 6 caracteres")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, Home::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)

    }

    private fun paramprofile(email: String, provider: ProviderType) {
        val profileintent = Intent(this, ThirdFragment::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        //startActivity(profileintent)
    }
}


