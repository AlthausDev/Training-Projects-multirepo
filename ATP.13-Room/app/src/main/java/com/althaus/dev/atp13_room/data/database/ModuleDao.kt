package com.althaus.dev.atp13_room.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.althaus.dev.atp13_room.data.database.Module;

/**
 * Data Access Object (DAO) para la entidad Module.
 * Proporciona métodos para realizar operaciones CRUD en la tabla 'module'.
 */
@Dao
interface ModuleDao {

    /**
     * Obtiene todos los módulos de la tabla 'module'.
     *
     * @return LiveData que emite una lista de módulos.
     */
    @Query("SELECT * FROM module")
    fun getAllModules(): LiveData<List<Module>>

    /**
     * Inserta un nuevo módulo en la tabla 'module'.
     *
     * @param module El módulo que se va a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(module: Module)

    /**
     * Elimina todos los módulos de la tabla 'module'.
     */
    @Query("DELETE FROM module")
    suspend fun clearAll()
}
