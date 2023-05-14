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
        db.execSQL("INSERT INTO usuarios (nombres, apellidos, email, user, password, tipo) VALUES ('Admin', 'Admin', 'admin@example.com', 'admin', 'holamundo', 'admin')")
        db.execSQL("INSERT INTO marcas (nombre) VALUES ('Ford')");
        db.execSQL("INSERT INTO marcas (nombre) VALUES ('Chevrolet')");
        db.execSQL("INSERT INTO marcas (nombre) VALUES ('Toyota')");
        db.execSQL("INSERT INTO marcas (nombre) VALUES ('Honda')");
        db.execSQL("INSERT INTO marcas (nombre) VALUES ('Nissan')");
        db.execSQL("INSERT INTO colores (descripcion) VALUES ('Rojo')");
        db.execSQL("INSERT INTO colores (descripcion) VALUES ('Azul')");
        db.execSQL("INSERT INTO colores (descripcion) VALUES ('Blanco')");
        db.execSQL("INSERT INTO colores (descripcion) VALUES ('Negro')");
        db.execSQL("INSERT INTO colores (descripcion) VALUES ('Gris')");
        db.execSQL("INSERT INTO tipo_automovil (descripcion) VALUES ('Sedan')");
        db.execSQL("INSERT INTO tipo_automovil (descripcion) VALUES ('Camioneta')");
        db.execSQL("INSERT INTO tipo_automovil (descripcion) VALUES ('Pickup')");
        db.execSQL("INSERT INTO tipo_automovil (descripcion) VALUES ('SUV')");
        db.execSQL("INSERT INTO tipo_automovil (descripcion) VALUES ('Deportivo')");
        db.execSQL("INSERT INTO automovil (modelo, numero_vin, numero_chasis, numero_motor, numero_asientos, anio, capacidad_asientos, precio, URI_IMG, descripcion, idmarca, idtipoautomovil, idcolor) VALUES ('Civic', '1HGEJ8146XL079184', '2HNYD18844H000000', 'K20A3', 5, 2020, 5, 25000.00, 'https://ejemplo.com/civic.png', 'Automóvil Honda Civic 2020', 4, 1, 2)");
        db.execSQL("INSERT INTO automovil (modelo, numero_vin, numero_chasis, numero_motor, numero_asientos, anio, capacidad_asientos, precio, URI_IMG, descripcion, idmarca, idtipoautomovil, idcolor) VALUES ('Mustang', '1FAFP404XWF195752', '1FTRF17L6XKA13715', '4.6 L', 4, 2022, 4, 35000.00, 'https://ejemplo.com/mustang.png', 'Automóvil Ford Mustang 2022', 1, 5, 3)");
        db.execSQL("INSERT INTO automovil (modelo, numero_vin, numero_chasis, numero_motor, numero_asientos, anio, capacidad_asientos, precio, URI_IMG, descripcion, idmarca, idtipoautomovil, idcolor) VALUES ('Cruze', '1G1BE5SM9G7293644', '1FTSW31P74EA84086', '1.4L Turbo', 5, 2019, 5, 20000.00, 'https://ejemplo.com/cruze.png', 'Automóvil Chevrolet Cruze 2019', 2, 1, 1)");



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
