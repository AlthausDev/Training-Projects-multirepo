package com.althaus.dev.atp13_room.core

import android.app.Application
import com.althaus.dev.atp13_room.data.AppRepository
import com.althaus.dev.atp13_room.data.database.ModuleDatabase

class ModuleApp : Application() {
    private val moduleDatabase: ModuleDatabase by lazy { ModuleDatabase.getDatabase(this) }
    val appRepository: AppRepository by lazy { AppRepository(moduleDatabase.moduleDao()) }
}
