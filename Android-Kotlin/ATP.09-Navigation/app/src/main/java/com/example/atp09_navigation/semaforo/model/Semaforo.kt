package com.mpd.pmdm.practicanavegacion1.semaforo.model

import android.graphics.Color

class Semaforo {
    // El atributo _color es variable, pero privado. Se modifica a través del método avanzar()
    private var _color: Int = Color.RED

    //Creamos un atributo color que hace de getter del anterior
    val color get() = _color

    fun avanzar() {
        _color = when (color) {
            Color.RED -> Color.YELLOW
            Color.YELLOW -> Color.GREEN
            else -> Color.RED
        }
    }
}