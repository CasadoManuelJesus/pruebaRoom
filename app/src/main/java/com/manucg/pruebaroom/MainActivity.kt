package com.manucg.pruebaroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.manucg.pruebaroom.modelo.conexiones.BDRoom
import com.manucg.pruebaroom.modelo.entidades.Usuario

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Realizamos la conexión y obetenemos el dao*/
        val conexion=BDRoom.getBaseDatos(this)
        val daoUsuario=conexion.DaoUsuario()

        /*BORRAR CONTENIDO DE USUARIO*/
      //  daoUsuario.borrarTablaUsuario()

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
        daoUsuario.deleteUsuario(daoUsuario.getUsuario("Manuel"))
        for (i in daoUsuario.getUsuarios())
            Log.d("deleteUsuario()", i.toString())

    }
}