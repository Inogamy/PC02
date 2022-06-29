package com.example.pc02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtdni: EditText = findViewById(R.id.txtNameLogin)
        val txtclave: EditText = findViewById(R.id.txtclavelogin)


        val btnRegister: Button = findViewById(R.id.btnCrear)
        btnRegister.setOnClickListener {
            val intent: Intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val btnIngresar: Button = findViewById(R.id.btnLogin)
        btnIngresar.setOnClickListener {

            val db = Firebase.firestore
            db.collection("Coleccion usuario")
                .whereEqualTo("dni", txtdni.text.toString())
                .whereEqualTo("clave", txtclave.text.toString())
                .get()
                .addOnSuccessListener { documents ->
                    var cont = 0
                    for (document in documents) {
                        cont++
                        println("${document.id} => ${document.data}")
                    }
                    if (cont != 0) {
                        Toast.makeText(this, "Acceso Permitido", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Dni o Clave incorrecta.", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener { exception ->//
                    Toast.makeText(this, "Error en conexion a la DB", Toast.LENGTH_LONG).show()
                }
        }
    }
}