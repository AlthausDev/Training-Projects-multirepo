package com.althaus.dev.ProcessMaster.data;

import java.io.Serializable;

// Clase que representa los datos de prueba para UDP
public class DatosPrueba implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numero;

    public DatosPrueba(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
