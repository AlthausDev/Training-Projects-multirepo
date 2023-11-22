package pa.althaus.dam.javaproyect.aeropuerto.entities;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

/**
 * @author samuelaa
 */
public class VueloDiario {

    private int idVuelo;
    private Date fechaVuelo;
    private Time horaSalidaReal;
    private Time horaLlegadaReal;
    private int plazasOcupadas;
    private float precioVuelo;


    public VueloDiario() {
    }

    public VueloDiario(int idVuelo, Date fechaVuelo, Time horaSalidaReal, Time horaLlegadaReal, int plazasOcupadas, float precioVuelo) {
        this.idVuelo = idVuelo;
        this.fechaVuelo = fechaVuelo;
        this.horaSalidaReal = horaSalidaReal;
        this.horaLlegadaReal = horaLlegadaReal;
        this.plazasOcupadas = plazasOcupadas;
        this.precioVuelo = precioVuelo;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Date getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(Date fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public Time getHoraSalidaReal() {
        return horaSalidaReal;
    }

    public void setHoraSalidaReal(Time horaSalidaReal) {
        this.horaSalidaReal = horaSalidaReal;
    }

    public Time getHoraLlegadaReal() {
        return horaLlegadaReal;
    }

    public void setHoraLlegadaReal(Time horaLlegadaReal) {
        this.horaLlegadaReal = horaLlegadaReal;
    }

    public int getPlazasOcupadas() {
        return plazasOcupadas;
    }

    public void setPlazasOcupadas(int plazasOcupadas) {
        this.plazasOcupadas = plazasOcupadas;
    }

    public float getPrecioVuelo() {
        return precioVuelo;
    }

    public void setPrecioVuelo(float precioVuelo) {
        this.precioVuelo = precioVuelo;
    }
}
