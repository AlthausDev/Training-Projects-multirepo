package com.althaus.dev.ProcessMaster.model;

import java.io.Serializable;

public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;
    private TipoDato tipoDato;
    private Object contenido;

    public Mensaje(TipoDato tipoDato, Object contenido) {
        this.tipoDato = tipoDato;
        this.contenido = contenido;
    }

    public TipoDato getTipoDato() {
        return tipoDato;
    }

    public Object getContenido() {
        return contenido;
    }
}