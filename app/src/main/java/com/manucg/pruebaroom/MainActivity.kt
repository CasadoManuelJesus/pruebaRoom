package com.manucg.pruebaroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.manucg.pruebaroom.modelo.conexiones.BDRoom
import com.manucg.pruebaroom.modelo.entidades.Libro
import com.manucg.pruebaroom.modelo.entidades.Usuario
import com.manucg.pruebaroom.modelo.interfaces.InterfaceDaoLibro
import com.manucg.pruebaroom.modelo.interfaces.InterfaceDaoUsuario

class MainActivity : AppCompatActivity() {
    lateinit var conexion : BDRoom
    lateinit var daoUsuario : InterfaceDaoUsuario
    lateinit var daoLibro: InterfaceDaoLibro
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
        pruebaUsuario()
        pruebaLibro()
        borraTablas()
    }
    fun setup(){
        /*Realizamos la conexión y obetenemos el dao*/
        conexion=BDRoom.getBaseDatos(this)
        daoUsuario=conexion.DaoUsuario()
        daoLibro= conexion.DaoLibro()

    }

    fun pruebaUsuario(){
        /*AÑADIENDO USUARIOS*/
        val usuario1 = Usuario("Pepe", 20, "Hombre")
        val usuario2 = Usuario("Maria", 13, "Mujer")
        val usuario3 = Usuario("Roberto", 42)
        daoUsuario.addUsuario(usuario1)
        daoUsuario.addUsuario(usuario2)
        daoUsuario.addUsuario(usuario3)

        /*RECOGIENDO LA LISTA DE USUARIOS*/
        for (i in daoUsuario.getUsuarios())
            Log.d("getUsuarios()", i.toString())

        /*RECOGIENDO UN USUARIO*/
        Log.d("getUsuario(\"Pepe\")", daoUsuario.getUsuario("Pepe").toString())

        /*ACTUALIZANDO UN USUARIO*/
        val usuario1Actualizado = daoUsuario.getUsuario("Pepe")
        usuario1Actualizado.nombre="Manuel"
        daoUsuario.updateUsuario(usuario1Actualizado)
        for (i in daoUsuario.getUsuarios())
            Log.d("updtaeUsuario()", i.toString())

        /*BORRANDO UN USUARIO*/
        /*
        daoUsuario.deleteUsuario(daoUsuario.getUsuario("Manuel"))
        for (i in daoUsuario.getUsuarios())
            Log.d("deleteUsuario()", i.toString())
        */
    }

    fun pruebaLibro(){
        /*AÑADIENDO LIBROS*/
        val libro1 = Libro("Libro 1", "Autor 1", daoUsuario.getUsuario("Manuel").idUsuario)
        val libro2 = Libro("Libro 2", "Autor 2", daoUsuario.getUsuario("Maria").idUsuario)
        val libro3 = Libro("Libro 3", "Autor 3", daoUsuario.getUsuario("Roberto").idUsuario)
        val libro4 = Libro("Libro 4", "Autor 1", daoUsuario.getUsuario("Manuel").idUsuario)
        val libro5 = Libro("Libro 5", "Autor 2", daoUsuario.getUsuario("Maria").idUsuario)
        val libro6 = Libro("Libro 6", "Autor 3", daoUsuario.getUsuario("Roberto").idUsuario)
        val libro7 = Libro("Libro 7", "Autor 1", daoUsuario.getUsuario("Manuel").idUsuario)
        val libro8 = Libro("Libro 8", "Autor 2", daoUsuario.getUsuario("Maria").idUsuario)
        val libro9 = Libro("Libro 9", "Autor 3", daoUsuario.getUsuario("Roberto").idUsuario)
        val libro10 = Libro("Libro 10", "Autor 1", daoUsuario.getUsuario("Manuel").idUsuario)
        val libro11 = Libro("Libro 11", "Autor 2", daoUsuario.getUsuario("Maria").idUsuario)
        val libro12 = Libro("Libro 12", "Autor 3", daoUsuario.getUsuario("Roberto").idUsuario)
        daoLibro.addLibro(libro1)
        daoLibro.addLibro(libro2)
        daoLibro.addLibro(libro3)
        daoLibro.addLibro(libro4)
        daoLibro.addLibro(libro5)
        daoLibro.addLibro(libro6)
        daoLibro.addLibro(libro7)
        daoLibro.addLibro(libro8)
        daoLibro.addLibro(libro9)
        daoLibro.addLibro(libro10)
        daoLibro.addLibro(libro11)
        daoLibro.addLibro(libro12)

        /*RECOGIENDO LA LISTA DE LIBROS*/
        for (i in daoLibro.getLibros())
            Log.d("getLibros()", i.toString())

        /*RECOGIENDO UN USUARIO*/
        Log.d("getLibro(\"Libro 1\")", daoLibro.getLibro("Libro 1").toString())

        /*ACTUALIZANDO UN LIBRO*/
        val libro1Actualizado = daoLibro.getLibro("Libro 1")
        libro1Actualizado.titulo="Libro modificado"
        daoLibro.updateLibro(libro1Actualizado)
        for (i in daoLibro.getLibros())
            Log.d("updateLibro()", i.toString())

        val idsAutores = mutableListOf<Int>(daoUsuario.getUsuario("Manuel").idUsuario,daoUsuario.getUsuario("Maria").idUsuario)
        daoLibro.getLibroByAutores(idsAutores).forEach{
            Log.d("Get libro by autores",it.toString())
        }

        val idUsuario = daoUsuario.getUsuario("Maria").idUsuario
        daoLibro.getNombreLibrosByUsuario(idUsuario).forEach {
            Log.d("Get nombre de libro by user", it)
        }


        /*BORRANDO UN LIBRO*/
        daoLibro.deleteLibro(daoLibro.getLibro("Libro 2"))
        for (i in daoLibro.getLibros())
            Log.d("deleteLibro()", i.toString())
    }

    fun borraTablas(){
        /*BORRAR CONTENIDO DE USUARIO*/
        daoLibro.borrarTablaLibro()
        daoUsuario.borrarTablaUsuario()

    }
}