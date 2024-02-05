package com.mpd.pmdm.practicaroommodulos.data

import com.althaus.dev.atp13_room.data.database.Module
import com.althaus.dev.atp13_room.data.database.ModuleDao

class AppRepository(private val moduleDao: ModuleDao) {

    val allModules = moduleDao.getAllModules()


    suspend fun insert(module: Module) {
        moduleDao.insert(module)
    }


    suspend fun clearAll() {
        moduleDao.clearAll()
    }

}