@Database(entities = [Usuario::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun DaoUsuario(): InterfaceDaoUsuario

    companion object {
        private var INSTANCE: AppDB? = null
        fun getBaseDatos(context: Context): AppDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "usuariosDB"
                ).allowMainThreadQueries().build()
                //Con migracion hay que incrementar la version y añadirle que migracion
                /*INSTANCE = Room.databaseBuilder(context.getApplicationContext(),BaseDatos.class,
                    "DietaBD").addMigrations(MIGRATION1_2).allowMainThreadQueries().build();*/
            }
            return INSTANCE as AppDB
        }
    }
}