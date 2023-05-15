package com.example.cars_motors.controladores

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.cars_motors.CarsMotorsDB
import com.example.cars_motors.modelos.ColorModel

class ColoresController(context: Context) {

    private val db: SQLiteDatabase = CarsMotorsDB(context).writableDatabase

    fun insertColor(color: ColorModel): Long {
        val values = ContentValues().apply {
            put("nombre", color.nombre)
        }
        return db.insert("colores", null, values)
    }

    @SuppressLint("Range")
    fun getColorById(id: Int): ColorModel? {
        val cursor = db.query(
            "colores", arrayOf("idcolor", "nombre"), "idcolor = ?",
            arrayOf(id.toString()), null, null, null
        )
        return if (cursor.moveToFirst()) {
            ColorModel(
                cursor.getInt(cursor.getColumnIndex("idcolor")),
                cursor.getString(cursor.getColumnIndex("nombre"))
            )
        } else {
            null
        }
    }

    @SuppressLint("Range")
    fun getAllColores(): List<ColorModel> {
        val colores = ArrayList<ColorModel>()
        val cursor = db.rawQuery("SELECT * FROM colores", null)
        while (cursor.moveToNext()) {
            val color = ColorModel(
                cursor.getInt(cursor.getColumnIndex("idcolor")),
                cursor.getString(cursor.getColumnIndex("descripcion"))
            )
            colores.add(color)
        }
        cursor.close()
        return colores
    }

    fun updateColor(color: ColorModel): Int {
        val values = ContentValues().apply {
            put("nombre", color.nombre)
        }
        return db.update(
            "colores", values, "idcolor = ?",
            arrayOf(color.id.toString())
        )
    }

    fun deleteColorbyid(id: Int): Int {
        return db.delete("colores", "idcolor = ?", arrayOf(id.toString()))
    }

    fun close() {
        db.close()
    }
}
