package com.example.cars_motors

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CarsMotorsDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "CarsMotorsDB"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crea las tablas de la base de datos
        db.execSQL("CREATE TABLE marcas (idmarca INTEGER PRIMARY KEY, nombre VARCHAR(45))")
        db.execSQL("CREATE TABLE colores (idcolor INTEGER PRIMARY KEY, descripcion VARCHAR(45))")
        db.execSQL("CREATE TABLE tipo_automovil (idtipo INTEGER PRIMARY KEY, descripcion VARCHAR(45))")
        db.execSQL("CREATE TABLE automovil (idautomovil INTEGER PRIMARY KEY, modelo VARCHAR(45),numero_vin VARCHAR(45),numero_chasis VARCHAR(45),numero_motor VARCHAR(45),numero_asientos INTEGER, anio YEAR, capacidad_asientos INT, precio DECIMAL,URI_IMG varchar(45), descripcion varchar(45),idmarca int, idtipoautomovil int, idcolor int, FOREIGN KEY(idmarca) REFERENCES marcas(idmarca), FOREIGN KEY(idtipoautomovil) REFERENCES tipo_automovil(idtipo), FOREIGN KEY(idcolor) REFERENCES colores(idcolor))")
        db.execSQL("CREATE TABLE favoritos_automovil (idfavoritosautomovil INTEGER PRIMARY KEY, idusuario INTEGER,idautomovil INTEGER, fecha_agregado TIMESTAMP, FOREIGN KEY(idusuario) REFERENCES usuarios(idusuario), FOREIGN KEY(idautomovil) REFERENCES automovil(idautomovil))")
        db.execSQL("CREATE TABLE usuarios (idusuario INTEGER PRIMARY KEY, nombres VARCHAR(25), apellidos VARCHAR(25), email VARCHAR(45), user VARCHAR(45), password VARCHAR(45), tipo VARCHAR(45))")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Actualiza las tablas de la base de datos
        db.execSQL("DROP TABLE IF EXISTS marcas")
        db.execSQL("DROP TABLE IF EXISTS colores")
        db.execSQL("DROP TABLE IF EXISTS tipo_automovil")
        db.execSQL("DROP TABLE IF EXISTS automovil")
        db.execSQL("DROP TABLE IF EXISTS favoritos_automovil")
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }
}
