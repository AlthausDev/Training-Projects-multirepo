package com.althaus.dev.atp13_room.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Base de datos Room que contiene la tabla 'module'.
 */
@Database(entities = [Module::class], version = 1, exportSchema = false)
abstract class ModuleDatabase : RoomDatabase() {

    /**
     * Retorna el Data Access Object (DAO) para la entidad Module.
     * Este DAO se utiliza para realizar operaciones en la tabla 'module'.
     */
    abstract fun moduleDao(): ModuleDao

    companion object {
        // Marcamos como @Volatile para asegurar que INSTANCE siempre esté actualizada y visible para otros hilos
        @Volatile
        private var INSTANCE: ModuleDatabase? = null

        /**
         * Retorna una instancia única de la base de datos.
         * Si la instancia no existe, la crea.
         * Este método es seguro para hilos gracias al uso de synchronized.
         */
        fun getDatabase(context: Context): ModuleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ModuleDatabase::class.java,
                    "module_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
