package com.manucg.pruebaroom.modelo.conexiones

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manucg.pruebaroom.modelo.entidades.Usuario
import com.manucg.pruebaroom.modelo.interfaces.InterfaceDaoUsuario

// Esta anotación define la base de datos utilizando Room.
// Indica que la base de datos consiste en la entidad Usuario y tiene la versión 1.
@Database(entities = [Usuario::class], version = 3)
abstract class BDRoom : RoomDatabase() {

    // Este método abstracto proporciona acceso al DAO
    abstract fun DaoUsuario(): InterfaceDaoUsuario

    // Este bloque define un companion object que contiene métodos estáticos.

    companion object {
        // INSTANCE almacena la instancia única de la base de datos.
        private var INSTANCE: BDRoom? = null

        // Este método estático crea y devuelve la instancia de la base de datos utilizando Room.
        fun getBaseDatos(context: Context): BDRoom {
            // Si INSTANCE es nulo, crea una nueva instancia de la base de datos.
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, BDRoom::class.java,
                    // Se especifica el nombre de la base de datos como "usuariosBD".
                    "usuariosBD"
                )
                    // Permite realizar consultas en el hilo principal
                    .allowMainThreadQueries()
                    .build()
            }
            // Devuelve la instancia existente o recién creada de la base de datos.
            return INSTANCE as BDRoom
        }
    }
}
