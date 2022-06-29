package com.example.pc02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pc02.models.UserModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val txtdni: EditText = findViewById(R.id.txtDniRegister)
        val txtname: EditText = findViewById(R.id.txtNameRegister)
        val txtclave: EditText = findViewById(R.id.txtClave)
        val txtrepclave: EditText = findViewById(R.id.txtRepclave)

        val btnregistrar: Button = findViewById(R.id.btnRegister)
        btnregistrar.setOnClickListener {
            if(txtclave.text.toString().equals(txtrepclave.text.toString())){
                Toast.makeText(this, "Todo bien", Toast.LENGTH_LONG).show()
                val usuario = UserModel(
                    txtdni.text.toString(),
                    txtname.text.toString(),
                    txtclave.text.toString()
                )
                val db = Firebase.firestore
                    db.collection("Coleccion usuario").add(usuario)
                        .addOnSuccessListener {
                            Toast.makeText(this,"Registro completo", Toast.LENGTH_LONG).show()
                            val intent : Intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }.addOnFailureListener{
                            Toast.makeText(this, "error al registrar", Toast.LENGTH_LONG).show()
                        }
            }else {
                Toast.makeText(this, "Las contraseñas deben ser iguales", Toast.LENGTH_LONG).show()
            }
        }
    }
}