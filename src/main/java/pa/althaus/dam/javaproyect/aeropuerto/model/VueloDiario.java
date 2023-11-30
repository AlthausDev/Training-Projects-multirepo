package pa.althaus.dam.javaproyect.aeropuerto.model;

import java.sql.Time;
import java.util.Date;

/**
 * @author samuelaa
 */
public class VueloDiario {

    private int codigoVuelo;
    private Date fechaVuelo;
    private Time horaSalidaReal;
    private Time horaLlegadaReal;
    private int plazasOcupadas;
    private float precioVuelo;


    public VueloDiario() {
    }

    public VueloDiario(int codigoVuelo, Date fechaVuelo, Time horaSalidaReal, Time horaLlegadaReal, int plazasOcupadas, float precioVuelo) {
        this.codigoVuelo = codigoVuelo;
        this.fechaVuelo = fechaVuelo;
        this.horaSalidaReal = horaSalidaReal;
        this.horaLlegadaReal = horaLlegadaReal;
        this.plazasOcupadas = plazasOcupadas;
        this.precioVuelo = precioVuelo;
    }

    public int getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(int codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
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
