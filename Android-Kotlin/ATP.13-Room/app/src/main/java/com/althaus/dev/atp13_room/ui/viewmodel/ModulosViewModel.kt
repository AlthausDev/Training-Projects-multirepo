package com.althaus.dev.atp13_room.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.althaus.dev.atp13_room.data.AppRepository
import com.althaus.dev.atp13_room.data.database.Module
import kotlinx.coroutines.launch

class ModulosViewModel(private val appRepository: AppRepository): ViewModel() {
    val allModules: LiveData<List<Module>> = appRepository.allModules

    fun insert(moduleName: String, moduleCredits: Int){
        viewModelScope.launch {
            val module = Module(name = moduleName, credits = moduleCredits)
            appRepository.insert(module)
        }
    }

    fun clearAll(){
        viewModelScope.launch {
            appRepository.clearAll()
        }
    }
}

class ModulosViewModelFactory(private val repository: AppRepository): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ModulosViewModel::class.java))
            return ModulosViewModel(repository) as T
        throw IllegalArgumentException("Error al instanciar el ViewModel")
    }
}
