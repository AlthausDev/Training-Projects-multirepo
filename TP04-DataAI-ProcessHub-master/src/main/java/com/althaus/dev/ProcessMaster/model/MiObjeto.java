package com.althaus.dev.ProcessMaster.model;

import java.io.Serializable;

// Clase que representa el objeto que se enviará entre el cliente y el servidor
public class MiObjeto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mensaje;

    public MiObjeto(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
