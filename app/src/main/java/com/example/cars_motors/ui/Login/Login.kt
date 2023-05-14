package com.example.cars_motors.ui.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cars_motors.MainActivity
import com.example.cars_motors.R
import com.example.cars_motors.controladores.UsuariosController
import com.example.cars_motors.modelos.Usuario
import com.example.cars_motors.ui.home.HomeFragment
import com.example.cars_motors.ui.home.HomeViewModel

class Login : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var loadingProgressBar: ProgressBar

    private lateinit var usuariosController: UsuariosController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuariosController = UsuariosController(this)

        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login)
        registerButton = findViewById(R.id.register)
        loadingProgressBar = findViewById(R.id.loading)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuario = usuariosController.getUsuarioByUsernameAndPassword(username, password)

            if (usuario != null) {
                Toast.makeText(this, "¡Bienvenido ${usuario.nombre}!", Toast.LENGTH_SHORT).show()
                UsuariosController.SessionManager.setCurrentUser(usuario)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

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
                nombre = "",
                apellido = "",
                email = "",
                user = username,
                password = password,
                tipo = "Cliente"
            )

            val idUsuario = usuariosController.insertUsuario(usuarioNuevo).toInt()

            if (idUsuario != -1) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                // Iniciar sesión automáticamente después del registro
                val usuarioRegistrado = usuariosController.getUsuarioById(idUsuario)
                if (usuarioRegistrado != null) {
                    val intent = Intent(this, MainActivity::class.java)
                    Toast.makeText(this, "¡Bienvenido ${usuarioRegistrado.nombre}!", Toast.LENGTH_SHORT).show()
                    UsuariosController.SessionManager.setCurrentUser(usuarioRegistrado)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
