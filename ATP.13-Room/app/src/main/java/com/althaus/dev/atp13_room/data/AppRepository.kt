package com.althaus.dev.atp13_room.data

import com.althaus.dev.atp13_room.data.database.Module
import com.althaus.dev.atp13_room.data.database.ModuleDao

class AppRepository(private val moduleDao: ModuleDao) {

    // LiveData que emite una lista de todos los módulos
    val allModules = moduleDao.getAllModules()

    /**
     * Inserta un módulo en la base de datos.
     * @param module El módulo que se va a insertar.
     */
    suspend fun insert(module: Module) {
        moduleDao.insert(module)
    }

    /**
     * Elimina todos los módulos de la base de datos.
     */
    suspend fun clearAll() {
        moduleDao.clearAll()
    }
}
