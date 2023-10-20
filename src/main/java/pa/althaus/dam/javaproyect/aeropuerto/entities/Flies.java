/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.althaus.dam.javaproyect.aeropuerto.entities;

import java.util.Date;

/**
 *
 * @author samuelaa
 */
public class Flies {
    private String fliesCode;
    private Airport destinyAirport;
    private int totalSeats;
    
    //Revisar, buscar java time;
    private Date departTime;   
    private Date arrivalTime;    
    private String operatingDays;

    public Flies() {
    }

    public Flies(String fliesCode, Airport destinyAirport, int totalSeats, Date departTime, Date arrivalTime, String operatingDays) {
        this.fliesCode = fliesCode;
        this.destinyAirport = destinyAirport;
        this.totalSeats = totalSeats;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.operatingDays = operatingDays;
    }

    public String getFliesCode() {
        return fliesCode;
    }

    public void setFliesCode(String fliesCode) {
        this.fliesCode = fliesCode;
    }

    public Airport getDestinyAirport() {
        return destinyAirport;
    }

    public void setDestinyAirport(Airport destinyAirport) {
        this.destinyAirport = destinyAirport;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getOperatingDays() {
        return operatingDays;
    }

    public void setOperatingDays(String operatingDays) {
        this.operatingDays = operatingDays;
    }
    
    
}
