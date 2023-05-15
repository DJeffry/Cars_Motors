package com.example.cars_motors.controladores

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.cars_motors.CarsMotorsDB
import com.example.cars_motors.modelos.Automovil
import java.util.*

class AutomovilController(context: Context) {

    private val db: SQLiteDatabase = CarsMotorsDB(context).writableDatabase

    fun insertAutomovil(automovil: Automovil): Long {
        val values = ContentValues().apply {
            put("modelo", automovil.modelo)
            put("numeroVin", automovil.numeroVin)
            put("numeroChasis", automovil.numeroChasis)
            put("numeroMotor", automovil.numeroMotor)
            put("numeroAsientos", automovil.numeroAsientos)
            put("anio", automovil.anio)
            put("capacidadAsientos", automovil.capacidadAsientos)
            put("precio", automovil.precio)
            put("URI_IMG", automovil.uriImg)
            put("descripcion", automovil.descripcion)
            put("idmarca", automovil.idMarca)
            put("idtipoautomovil", automovil.idTipoAutomovil)
            put("idcolor", automovil.idColor)
        }
        return db.insert("automovil", null, values)
    }

    @SuppressLint("Range")
    fun getAutomovilById(id: Int): Automovil? {
        val cursor = db.query(
            "automovil", arrayOf(
                "idautomovil",
                "modelo",
                "numero_vin",
                "numero_chasis",
                "numero_motor",
                "numero_asientos",
                "anio",
                "capacidad_asientos",
                "precio",
                "URI_IMG",
                "descripcion",
                "idmarca",
                "idtipoautomovil",
                "idcolor"
            ),
            "idautomovil = ?", arrayOf(id.toString()), null, null, null
        )

        return if (cursor.moveToFirst()) {
            Automovil(
                cursor.getInt(cursor.getColumnIndex("idautomovil")),
                cursor.getString(cursor.getColumnIndex("modelo")),
                cursor.getString(cursor.getColumnIndex("numero_vin")),
                cursor.getString(cursor.getColumnIndex("numero_chasis")),
                cursor.getString(cursor.getColumnIndex("numero_motor")),
                cursor.getInt(cursor.getColumnIndex("numero_asientos")),
                cursor.getInt(cursor.getColumnIndex("anio")),
                cursor.getInt(cursor.getColumnIndex("capacidad_asientos")),
                cursor.getDouble(cursor.getColumnIndex("precio")),
                cursor.getString(cursor.getColumnIndex("URI_IMG")),
                cursor.getString(cursor.getColumnIndex("descripcion")),
                cursor.getInt(cursor.getColumnIndex("idmarca")),
                cursor.getInt(cursor.getColumnIndex("idtipoautomovil")),
                cursor.getInt(cursor.getColumnIndex("idcolor"))
            )
        } else {
            null
        }
    }

    @SuppressLint("Range")
    fun getAllAutomoviles(): List<Automovil> {
        val automoviles = ArrayList<Automovil>()
        val cursor = db.rawQuery("SELECT * FROM automovil", null)
        while (cursor.moveToNext()) {
            val automovil = Automovil(
                cursor.getInt(cursor.getColumnIndex("idautomovil")),
                cursor.getString(cursor.getColumnIndex("modelo")),
                cursor.getString(cursor.getColumnIndex("numero_vin")),
                cursor.getString(cursor.getColumnIndex("numero_chasis")),
                cursor.getString(cursor.getColumnIndex("numero_motor")),
                cursor.getInt(cursor.getColumnIndex("numero_asientos")),
                cursor.getInt(cursor.getColumnIndex("anio")),
                cursor.getInt(cursor.getColumnIndex("capacidad_asientos")),
                cursor.getDouble(cursor.getColumnIndex("precio")),
                cursor.getString(cursor.getColumnIndex("URI_IMG")),
                cursor.getString(cursor.getColumnIndex("descripcion")),
                cursor.getInt(cursor.getColumnIndex("idmarca")),
                cursor.getInt(cursor.getColumnIndex("idtipoautomovil")),
                cursor.getInt(cursor.getColumnIndex("idcolor"))
            )
            automoviles.add(automovil)
        }
        cursor.close()
        return automoviles
    }

    fun updateAutomovil(automovil: Automovil): Int {
        val values = ContentValues().apply {
            put("modelo", automovil.modelo)
            put("numero_vin", automovil.numeroVin)
            put("numero_chasis", automovil.numeroChasis)
            put("numero_motor", automovil.numeroMotor)
            put("numero_asientos", automovil.numeroAsientos)
            put("anio", automovil.anio)
            put("capacidad_asientos", automovil.capacidadAsientos)
            put("precio", automovil.precio)
            put("URI_IMG", automovil.uriImg)
            put("descripcion", automovil.descripcion)
            put("idmarca", automovil.idMarca)
            put("idtipoautomovil", automovil.idTipoAutomovil)
            put("idcolor", automovil.idColor)
        }
        return db.update(
            "automovil", values, "idautomovil = ?",
            arrayOf(automovil.id.toString())
        )
    }

    fun deleteAutomovilById(id: Int): Int {
        return db.delete("automovil", "idautomovil = ?", arrayOf(id.toString()))
    }

}