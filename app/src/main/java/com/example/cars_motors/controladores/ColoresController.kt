package com.example.cars_motors.controladores

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.cars_motors.CarsMotorsDB
import com.example.cars_motors.modelos.Color

class ColoresController(context: Context) {

    private val db: SQLiteDatabase = CarsMotorsDB(context).writableDatabase

    fun insertColor(color: Color): Long {
        val values = ContentValues().apply {
            put("nombre", color.nombre)
        }
        return db.insert("colores", null, values)
    }

    @SuppressLint("Range")
    fun getColorById(id: Int): Color? {
        val cursor = db.query(
            "colores", arrayOf("idcolor", "nombre"), "idcolor = ?",
            arrayOf(id.toString()), null, null, null
        )
        return if (cursor.moveToFirst()) {
            Color(
                cursor.getInt(cursor.getColumnIndex("idcolor")),
                cursor.getString(cursor.getColumnIndex("nombre"))
            )
        } else {
            null
        }
    }

    @SuppressLint("Range")
    fun getAllColores(): List<Color> {
        val colores = ArrayList<Color>()
        val cursor = db.rawQuery("SELECT * FROM colores", null)
        while (cursor.moveToNext()) {
            val color = Color(
                cursor.getInt(cursor.getColumnIndex("idcolor")),
                cursor.getString(cursor.getColumnIndex("nombre"))
            )
            colores.add(color)
        }
        cursor.close()
        return colores
    }

    fun updateColor(color: Color): Int {
        val values = ContentValues().apply {
            put("nombre", color.nombre)
        }
        return db.update(
            "colores", values, "idcolor = ?",
            arrayOf(color.id.toString())
        )
    }

    fun deleteColor(id: Int): Int {
        return db.delete("colores", "idcolor = ?", arrayOf(id.toString()))
    }

    fun close() {
        db.close()
    }
}
