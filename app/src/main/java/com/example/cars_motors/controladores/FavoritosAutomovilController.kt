package com.example.cars_motors.controladores

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.cars_motors.CarsMotorsDB
import com.example.cars_motors.modelos.FavoritoAutomovil
import java.sql.Timestamp

class FavoritosAutomovilController(context: Context) {

    private val db: SQLiteDatabase = CarsMotorsDB(context).writableDatabase

    fun insertFavoritoAutomovil(favorito: FavoritoAutomovil): Long {
        val values = ContentValues().apply {
            put("idautomovil", favorito.idAutomovil)
            put("idusuario", favorito.idUsuario)
            put("fecha_agregado", favorito.fechaAgregado.toString())
        }
        return db.insert("favoritos_automovil", null, values)
    }

    @SuppressLint("Range")
    fun getFavoritoAutomovilById(id: Int): FavoritoAutomovil? {
        val cursor = db.query(
            "favoritos_automovil", arrayOf("idfavoritosautomovil", "idautomovil", "idusuario", "fecha_agregado"), "idfavoritosautomovil = ?",
            arrayOf(id.toString()), null, null, null
        )
        return if (cursor.moveToFirst()) {
            FavoritoAutomovil(
                cursor.getInt(cursor.getColumnIndex("idfavoritosautomovil")),
                cursor.getInt(cursor.getColumnIndex("idautomovil")),
                cursor.getInt(cursor.getColumnIndex("idusuario")),
                Timestamp.valueOf(cursor.getString(cursor.getColumnIndex("fecha_agregado")))
            )
        } else {
            null
        }
    }

    @SuppressLint("Range")
    fun getAllFavoritosAutomovil(): List<FavoritoAutomovil> {
        val favoritos = ArrayList<FavoritoAutomovil>()
        val cursor = db.rawQuery("SELECT * FROM favoritos_automovil", null)
        while (cursor.moveToNext()) {
            val favorito = FavoritoAutomovil(
                cursor.getInt(cursor.getColumnIndex("idfavoritosautomovil")),
                cursor.getInt(cursor.getColumnIndex("idautomovil")),
                cursor.getInt(cursor.getColumnIndex("idusuario")),
                Timestamp.valueOf(cursor.getString(cursor.getColumnIndex("fecha_agregado")))
            )
            favoritos.add(favorito)
        }
        cursor.close()
        return favoritos
    }

    fun deleteFavoritoAutomovil(id: Int): Int {
        return db.delete("favoritos_automovil", "idfavoritosautomovil = ?", arrayOf(id.toString()))
    }

    fun close() {
        db.close()
    }
}
