package com.manucg.pruebaroom.modelo.interfaces

import androidx.room.*
import com.manucg.pruebaroom.modelo.entidades.Libro

@Dao
interface InterfaceDaoLibro {

    @Insert
    fun addLibro(libro: Libro)
    @Query("SELECT * FROM libro")
    fun getLibros() : MutableList<Libro>
    @Query("SELECT * FROM libro WHERE titulo LIKE :titulo")
    fun getLibro(titulo: String) : Libro
    @Query("SELECT * FROM libro WHERE usuario IN (:usuarios)")
    fun getLibroByAutores(usuarios : MutableList<Int>) : MutableList<Libro>
    @Query("SELECT l.titulo FROM libro l INNER JOIN Usuario u ON l.usuario = u.id_usuario WHERE u.id_usuario = :usuario")
    fun getNombreLibrosByUsuario(usuario: Int) : List<String>
    @Update
    fun updateLibro(libro: Libro)
    @Delete
    fun deleteLibro(libro:Libro)
    @Query("DELETE FROM libro") // Reemplaza 'mi_tabla' con el nombre real de tu tabla
    fun borrarTablaLibro()

}