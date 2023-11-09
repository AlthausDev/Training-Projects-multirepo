package pa.althaus.dam.javaproyect.aeropuerto.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author samuelaa
 */
public class VuelosDiarios {
    
    private HashMap <String, Vuelos> vuelosDia;
    private Date fechaVuelo;
    private Date horaSalidaVuelo;
    private Date horaLlegadaVuelo;
    
    private int asientosOcupados;
    private float Precios;
    
    public VuelosDiarios() {
    }

    public VuelosDiarios(Date fechaVuelo, Date horaSalidaVuelo, Date horaLlegadaVuelo, int asientosOcupados, float Precios) {
        this.fechaVuelo = fechaVuelo;
        this.horaSalidaVuelo = horaSalidaVuelo;
        this.horaLlegadaVuelo = horaLlegadaVuelo;
        this.asientosOcupados = asientosOcupados;
        this.Precios = Precios;
    }

    public Date getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(Date fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public Date getHoraSalidaVuelo() {
        return horaSalidaVuelo;
    }

    public void setHoraSalidaVuelo(Date horaSalidaVuelo) {
        this.horaSalidaVuelo = horaSalidaVuelo;
    }

    public Date getHoraLlegadaVuelo() {
        return horaLlegadaVuelo;
    }

    public void setHoraLlegadaVuelo(Date horaLlegadaVuelo) {
        this.horaLlegadaVuelo = horaLlegadaVuelo;
    }

    public int getAsientosOcupados() {
        return asientosOcupados;
    }

    public void setAsientosOcupados(int asientosOcupados) {
        this.asientosOcupados = asientosOcupados;
    }

    public float getPrecios() {
        return Precios;
    }

    public void setPrecios(float Precios) {
        this.Precios = Precios;
    }    
}
