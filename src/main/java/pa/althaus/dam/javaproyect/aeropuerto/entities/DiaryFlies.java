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
public class DiaryFlies {
    private Date flyDay;
    private Date flyDepartHour;
    private Date flyArrivalHour;
    
    private int ocupedSeats;
    private float price;
    
    //Objeto de vuelo;

    public DiaryFlies() {
    }

    public DiaryFlies(Date flyDay, Date flyDepartHour, Date flyArrivalHour, int ocupedSeats, float price) {
        Flies fly = new Flies();
        this.flyDay = flyDay;
        this.flyDepartHour = flyDepartHour;
        this.flyArrivalHour = flyArrivalHour;
        this.ocupedSeats = ocupedSeats;
        this.price = price;
    }

    public Date getFlyDay() {
        return flyDay;
    }

    public void setFlyDay(Date flyDay) {
        this.flyDay = flyDay;
    }

    public Date getFlyDepartHour() {
        return flyDepartHour;
    }

    public void setFlyDepartHour(Date flyDepartHour) {
        this.flyDepartHour = flyDepartHour;
    }

    public Date getFlyArrivalHour() {
        return flyArrivalHour;
    }

    public void setFlyArrivalHour(Date flyArrivalHour) {
        this.flyArrivalHour = flyArrivalHour;
    }

    public int getOcupedSeats() {
        return ocupedSeats;
    }

    public void setOcupedSeats(int ocupedSeats) {
        this.ocupedSeats = ocupedSeats;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
