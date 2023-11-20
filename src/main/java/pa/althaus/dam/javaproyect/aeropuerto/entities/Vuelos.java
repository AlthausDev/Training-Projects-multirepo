package pa.althaus.dam.javaproyect.aeropuerto.entities;

import java.util.Date;

/**
 * @author samuelaa
 */
public class Vuelos {
    private String codigoVuelo;
    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private int asientosTotales;

    //Revisar, buscar java time;
    private Date horaSalida;
    private Date horaLlegada;
    private String diasSemanaOpera;

    public Vuelos() {
    }

    public Vuelos(String codigoVuelo, Aeropuerto aeropuertoDestino, int asientosTotales, Date horaSalida, Date horaLlegada, String diasSemanaOpera) {
        this.codigoVuelo = codigoVuelo;
        this.aeropuertoDestino = aeropuertoDestino;
        this.asientosTotales = asientosTotales;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.diasSemanaOpera = diasSemanaOpera;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public int getAsientosTotales() {
        return asientosTotales;
    }

    public void setAsientosTotales(int asientosTotales) {
        this.asientosTotales = asientosTotales;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getDiasSemanaOpera() {
        return diasSemanaOpera;
    }

    public void setDiasSemanaOpera(String diasSemanaOpera) {
        this.diasSemanaOpera = diasSemanaOpera;
    }
    
}
