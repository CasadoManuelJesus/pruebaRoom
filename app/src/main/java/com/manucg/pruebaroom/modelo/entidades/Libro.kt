package com.manucg.pruebaroom.modelo.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName="libro",
        foreignKeys = [
            ForeignKey(
                entity = Usuario::class,
                parentColumns = ["id_usuario"],
                childColumns = ["usuario"]
            )
        ]
)

data class Libro(
    @ColumnInfo("titulo")
    var titulo : String,
    @ColumnInfo("genero")
    var genero : String,
    @ColumnInfo( "usuario")
    var usuario : Int
){
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="idLibro")
    var idLibro = 0

    override fun toString(): String {
        return "Libro(" +
                "idLibro=$idLibro," +
                " titulo='$titulo'," +
                " usuario=$usuario)"
    }

}
