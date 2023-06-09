package com.example.cars_motors.controladores

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.cars_motors.CarsMotorsDB
import com.example.cars_motors.modelos.TipoAutomovil

class TiposAutomovilController(context: Context) {

    private val db: SQLiteDatabase = CarsMotorsDB(context).writableDatabase

    fun insertTipoAutomovil(tipoAutomovil: TipoAutomovil): Long {
        val values = ContentValues().apply {
            put("descripcion", tipoAutomovil.descripcion)
        }
        return db.insert("tipos_automovil", null, values)
    }

    @SuppressLint("Range")
    fun getTipoAutomovilById(id: Int?): TipoAutomovil? {
        val cursor = db.query(
            "tipo_automovil", arrayOf("idtipo", "descripcion"), "idtipo = ?",
            arrayOf(id.toString()), null, null, null
        )
        return if (cursor.moveToFirst()) {
            TipoAutomovil(
                cursor.getInt(cursor.getColumnIndex("idtipo")),
                cursor.getString(cursor.getColumnIndex("descripcion"))
            )
        } else {
            null
        }
    }

    @SuppressLint("Range")
    fun getAllTiposAutomovil(): List<TipoAutomovil> {
        val tiposAutomovil = ArrayList<TipoAutomovil>()
        val cursor = db.rawQuery("SELECT * FROM tipo_automovil", null)
        while (cursor.moveToNext()) {
            val tipoAutomovil = TipoAutomovil(
                cursor.getInt(cursor.getColumnIndex("idtipo")),
                cursor.getString(cursor.getColumnIndex("descripcion"))
            )
            tiposAutomovil.add(tipoAutomovil)
        }
        cursor.close()
        return tiposAutomovil
    }

    fun updateTipoAutomovil(tipoAutomovil: TipoAutomovil): Int {
        val values = ContentValues().apply {
            put("descripcion", tipoAutomovil.descripcion)
        }
        return db.update(
            "tipo_automovil", values, "idtipo = ?",
            arrayOf(tipoAutomovil.id.toString())
        )
    }

    fun deleteTipoAutomovilbyid(id: Int): Int {
        return db.delete("tipos_automovil", "idtipo = ?", arrayOf(id.toString()))
    }

    fun close() {
        db.close()
    }
}
