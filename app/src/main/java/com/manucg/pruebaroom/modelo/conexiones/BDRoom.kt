package com.manucg.pruebaroom.modelo.conexiones

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.manucg.pruebaroom.modelo.entidades.Libro
import com.manucg.pruebaroom.modelo.entidades.Usuario
import com.manucg.pruebaroom.modelo.interfaces.InterfaceDaoLibro
import com.manucg.pruebaroom.modelo.interfaces.InterfaceDaoUsuario

// Esta anotación define la base de datos utilizando Room.
// Cambio la versión a 2 debido a la migración
@Database(entities = [Usuario::class, Libro::class], version = 4)
abstract class BDRoom : RoomDatabase() {

    // Este método abstracto proporciona acceso al DAO
    abstract fun DaoUsuario(): InterfaceDaoUsuario

    //Acceso al DAO de libro
    abstract fun DaoLibro():InterfaceDaoLibro

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
                    //Añado la migración
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                    .build()
            }
            // Devuelve la instancia existente o recién creada de la base de datos.
            return INSTANCE as BDRoom
        }
        //Defino la migración
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE usuario ADD COLUMN descripcion TEXT")
            }
        }

        val MIGRATION_2_3: Migration = object : Migration(2,3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS libro (" +
                        "idLibro INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        "titulo TEXT NOT NULL," +
                        "genero TEXT NOT NULL," +
                        "usuario INTEGER NOT NULL," +
                        "FOREIGN KEY(usuario) REFERENCES usuario(id_usuario)" +
                        ")")
            }
        }

        val MIGRATION_3_4 : Migration = object : Migration(3,4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE usuario ADD COLUMN prueba1 TEXT")
                database.execSQL("ALTER TABLE usuario ADD COLUMN prueba2 INTEGER")
            }
        }
    }
}
