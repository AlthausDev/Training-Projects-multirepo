/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.althaus.dam.javaproyect.aeropuerto.entities;

/**
 *
 * @author samuelaa
 */
public class Company {
    private int prefix;
    private String code;
    private String compName;
    private String direcction;
    private String township;
    private String tlfPassenger;
    private String tlfAirport;

    public Company() {
    }

    public Company(int prefix, String code, String compName, String direcction, String township, String tlfPassenger, String tlfAirport) {
        this.prefix = prefix;
        this.code = code;
        this.compName = compName;
        this.direcction = direcction;
        this.township = township;
        this.tlfPassenger = tlfPassenger;
        this.tlfAirport = tlfAirport;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getDirecction() {
        return direcction;
    }

    public void setDirecction(String direcction) {
        this.direcction = direcction;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getTlfPassenger() {
        return tlfPassenger;
    }

    public void setTlfPassenger(String tlfPassenger) {
        this.tlfPassenger = tlfPassenger;
    }

    public String getTlfAirport() {
        return tlfAirport;
    }

    public void setTlfAirport(String tlfAirport) {
        this.tlfAirport = tlfAirport;
    }
    
      
   
}
