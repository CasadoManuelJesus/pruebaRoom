package com.manucg.pruebaroom.modelo.interfaces

import androidx.room.*
import com.manucg.pruebaroom.modelo.entidades.Usuario

// Esta anotación indica que esta interfaz es un DAO para Room.
@Dao
interface InterfaceDaoUsuario {

    // Esta anotación indica que el método realiza la inserción de datos en la base de datos.
    @Insert
    fun addUsuario(nuevoUsuario : Usuario)

    // Esta anotación indica que el método realizará una consulta en la base de datos
    @Query("SELECT * FROM usuario")
    fun getUsuarios(): MutableList<Usuario>

    // El parámetro ":nombre" se reemplazará con el valor proporcionado al llamar a la función.
    @Query("SELECT * FROM usuario WHERE nombre LIKE :nombre")
    fun getUsuario(nombre : String) : Usuario

    // Esta anotación indica que el método actualizará los datos en la base de datos.
    @Update
    fun updateUsuario(nuevoUsuario : Usuario)

    // Esta anotación indica que el método eliminará los datos de la base de datos.
    @Delete
    fun deleteUsuario(usuario:Usuario)

    @Query("DELETE FROM usuario") // Reemplaza 'mi_tabla' con el nombre real de tu tabla
    fun borrarTablaUsuario()
}
