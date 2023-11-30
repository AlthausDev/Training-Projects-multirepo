package pa.althaus.dam.javaproyect.aeropuerto.model;

import java.sql.Time;
import java.util.Date;

/**
 * @author samuelaa
 */
public class Vuelo {
    private String codigoVuelo;
    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private int plazasTotales;
    private Time horaSalida;
    private Time horaLlegada;
    private String diasOpera;

    public Vuelo() {
    }

    public Vuelo(String codigoVuelo, Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino, int plazasTotales, Time horaSalida, Time horaLlegada, String diasOpera) {
        this.codigoVuelo = codigoVuelo;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.plazasTotales = plazasTotales;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.diasOpera = diasOpera;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public int getPlazasTotales() {
        return plazasTotales;
    }

    public void setPlazasTotales(int plazasTotales) {
        this.plazasTotales = plazasTotales;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Time getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Time horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getDiasOpera() {
        return diasOpera;
    }

    public void setDiasOpera(String diasOpera) {
        this.diasOpera = diasOpera;
    }
}
