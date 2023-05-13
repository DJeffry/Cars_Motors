package com.example.cars_motors.controladores

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.cars_motors.CarsMotorsDB
import com.example.cars_motors.modelos.Marca
import java.util.*

class MarcasController(context: Context) {

    private val db: SQLiteDatabase = CarsMotorsDB(context).writableDatabase

    fun insertMarca(marca: Marca): Long {
        val values = ContentValues().apply {
            put("nombre", marca.nombre)
        }
        return db.insert("marcas", null, values)
    }

    @SuppressLint("Range")
    fun getMarcaById(id: Int): Marca? {
        val cursor = db.query(
            "marcas", arrayOf("idmarca", "nombre"), "idmarca = ?",
            arrayOf(id.toString()), null, null, null
        )
        return if (cursor.moveToFirst()) {
            Marca(
                cursor.getInt(cursor.getColumnIndex("idmarca")),
                cursor.getString(cursor.getColumnIndex("nombre"))
            )
        } else {
            null
        }
    }

    @SuppressLint("Range")
    fun getAllMarcas(): List<Marca> {
        val marcas = ArrayList<Marca>()
        val cursor = db.rawQuery("SELECT * FROM marcas", null)
        while (cursor.moveToNext()) {
            val marca = Marca(
                cursor.getInt(cursor.getColumnIndex("idmarca")),
                cursor.getString(cursor.getColumnIndex("nombre"))
            )
            marcas.add(marca)
        }
        cursor.close()
        return marcas
    }

    fun updateMarca(marca: Marca): Int {
        val values = ContentValues().apply {
            put("nombre", marca.nombre)
        }
        return db.update(
            "marcas", values, "idmarca = ?",
            arrayOf(marca.id.toString())
        )
    }

    fun deleteMarca(id: Int): Int {
        return db.delete("marcas", "idmarca = ?", arrayOf(id.toString()))
    }

    fun close() {
        db.close()
    }
}
