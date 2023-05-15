package com.example.cars_motors.ui.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.cars_motors.R
import com.example.cars_motors.controladores.UsuariosController
import com.example.cars_motors.modelos.Usuario


class Register : AppCompatActivity() {
    private lateinit var nameEditText:EditText
    private lateinit var lastnameEditText:EditText
    private lateinit var emailEditText:EditText
    private lateinit var usernameREditText: EditText
    private lateinit var passwordREditText: EditText
    private lateinit var registerRButton: Button
    private lateinit var usuariosController: UsuariosController

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        usuariosController = UsuariosController(this)
        registerRButton = findViewById(R.id.registerR)
        nameEditText=findViewById(R.id.username)
        lastnameEditText=findViewById(R.id.lastname)
       emailEditText=findViewById(R.id.email)
        usernameREditText=findViewById(R.id.usernameR)
        passwordREditText=findViewById(R.id.paswordR)
        registerRButton.setOnClickListener {
            val nombre = nameEditText.text.toString().trim()
            val apellido= lastnameEditText.toString().trim()
            val email = emailEditText.toString().trim()
            val username = usernameREditText.text.toString().trim()
            val password = passwordREditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuarioExistente = usuariosController.getUsuarioByUser(username)

            if (usuarioExistente != null) {
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuarioNuevo = Usuario(
                nombre = nombre,
                apellido = apellido,
                email = email,
                user = username,
                password = password,
                tipo = "Cliente"
            )

            val idUsuario = usuariosController.insertUsuario(usuarioNuevo).toInt()

            if (idUsuario != -1) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                val usuarioRegistrado = usuariosController.getUsuarioById(idUsuario)
                if (usuarioRegistrado != null) {
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}