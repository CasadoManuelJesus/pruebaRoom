package com.manucg.pruebaroom.modelo.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

// @Entity nos servirá para definir la entidad de Room
@Entity(tableName = "usuario")
// La clase deberá ser Serializable
data class Usuario(
    // ColumnInfo nos sirve para definir una columna de nuestra tabla
    @ColumnInfo(name = "nombre")
    var nombre: String,
    @ColumnInfo(name = "genero")
    var genero: String = "Sin especificar",
    @ColumnInfo(name = "edad")
    var edad: Int
) : Serializable {

    // Clave primaria autoincremental
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    var idUsuario = 0

    // También podemos definir columnas en el cuerpo de la clase
    @ColumnInfo(name = "mayor_edad")
    var mayorEdad = "Sin determinar"

    // @Ignore hará que Room no tome en cuenta la variable
    @Ignore
    var comentarios: MutableList<String> = mutableListOf()

    // Inicializador que se ejecuta al crear una instancia de Usuario
    init {
        // Si la edad es mayor o igual a 18, entonces se considera mayor de edad y se actualiza el valor de "mayorEdad"
        if (edad >= 18)
            mayorEdad = "Si"
        else
            mayorEdad = "No"
    }
}
