package com.example.cars_motors.controladores

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.cars_motors.CarsMotorsDB
import com.example.cars_motors.modelos.Usuario
import java.util.*

class UsuariosController(context: Context) {

    private val db: SQLiteDatabase = CarsMotorsDB(context).writableDatabase

    object SessionManager {
        private var currentUser: Usuario? = null

        fun setCurrentUser(user: Usuario) {
            currentUser = user
        }

        fun getCurrentUser(): Usuario? {
            return currentUser
        }
    }

    fun insertUsuario(usuario: Usuario): Long {
        val values = ContentValues().apply {
            put("nombres", usuario.nombre)
            put("apellidos", usuario.apellido)
            put("email", usuario.email)
            put("user", usuario.user)
            put("password", usuario.password)
            put("tipo", usuario.tipo)
        }
        return db.insert("usuarios", null, values)
    }

    @SuppressLint("Range")
    private fun getUsuarioByCursor(cursor: Cursor): Usuario {
        return Usuario(
            cursor.getInt(cursor.getColumnIndex("idusuario")),
            cursor.getString(cursor.getColumnIndex("nombres")),
            cursor.getString(cursor.getColumnIndex("apellidos")),
            cursor.getString(cursor.getColumnIndex("email")),
            cursor.getString(cursor.getColumnIndex("user")),
            cursor.getString(cursor.getColumnIndex("password")),
            cursor.getString(cursor.getColumnIndex("tipo"))
        )
    }

    fun getUsuarioById(id: Int): Usuario? {
        val cursor = db.query(
            "usuarios", arrayOf("idusuario", "nombres", "apellidos", "email", "user", "password", "tipo"), "idusuario = ?",
            arrayOf(id.toString()), null, null, null
        )
        return if (cursor.moveToFirst()) {
            getUsuarioByCursor(cursor)
        } else {
            null
        }
    }

    fun getUsuarioByEmail(email: String): Usuario? {
        val cursor = db.query(
            "usuarios", arrayOf("idusuario", "nombres", "apellidos", "email", "user", "password", "tipo"), "email = ?",
            arrayOf(email), null, null, null
        )
        return if (cursor.moveToFirst()) {
            getUsuarioByCursor(cursor)
        } else {
            null
        }
    }

    fun getUsuarioByUser(user: String): Usuario? {
        val cursor = db.query(
            "usuarios", arrayOf("idusuario", "nombres", "apellidos", "email", "user", "password", "tipo"), "user = ?",
            arrayOf(user), null, null, null
        )
        return if (cursor.moveToFirst()) {
            getUsuarioByCursor(cursor)
        } else {
            null
        }
    }

    fun getAllUsuarios(): List<Usuario> {
        val usuarios = ArrayList<Usuario>()
        val cursor = db.rawQuery("SELECT * FROM usuarios", null)
        while (cursor.moveToNext()) {
            val usuario = getUsuarioByCursor(cursor)
            usuarios.add(usuario)
        }
        cursor.close()
        return usuarios
    }

    fun updateUsuario(usuario: Usuario): Int {
        val values = ContentValues().apply {
            put("nombres", usuario.nombre)
            put("apellidos", usuario.apellido)
            put("email", usuario.email)
            put("user", usuario.user)
            put("password", usuario.password)
            put("tipo", usuario.tipo)
        }
        return db.update(
            "usuarios", values, "idusuario = ?",
            arrayOf(usuario.id.toString())
        )
    }

    fun getUsuarioByUsernameAndPassword(username: String, password: String): Usuario? {
        val selection = "user = ? AND password = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query("usuarios", null, selection, selectionArgs, null, null, null)
        return if (cursor.moveToFirst()) {
            val usuario = getUsuarioByCursor(cursor)
            cursor.close()
            usuario
        } else {
            cursor.close()
            null
        }
    }

    fun deleteUsuario(id: Int): Int {
        return db.delete("usuarios", "idusuario = ?", arrayOf(id.toString()))
    }

    fun close() {
        db.close()
    }
}
