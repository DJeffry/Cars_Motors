package com.example.cars_motors.modelos

import java.sql.Timestamp

data class FavoritoAutomovil(
    var id: Int = 0,
    var idAutomovil: Int = 0,
    var idUsuario: Int = 0,
    var fechaAgregado: Timestamp = Timestamp(System.currentTimeMillis())
)
