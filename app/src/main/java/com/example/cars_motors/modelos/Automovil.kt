package com.example.cars_motors.modelos

data class Automovil(
    var id: Int = 0,
    var modelo: String = "",
    var numeroVin: String = "",
    var numeroChasis: String = "",
    var numeroMotor: String = "",
    var numeroAsientos: Int = 0,
    var anio: Int = 0,
    var capacidadAsientos: Int = 0,
    var precio: Double = 0.0,
    var uriImg: String = "",
    var descripcion: String = "",
    var idMarca: Int = 0,
    var idTipoAutomovil: Int = 0,
    var idColor: Int = 0
)
