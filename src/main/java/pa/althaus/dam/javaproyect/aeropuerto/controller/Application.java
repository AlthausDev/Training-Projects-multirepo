/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pa.althaus.dam.javaproyect.aeropuerto.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static java.time.LocalDate.now;

import java.util.Date;
import java.util.Locale;

/**
 * @author S.Althaus
 */
public class Application {

    public static void main(String[] args) {
        new Sesion();
    }
     
    
    /*public static void main(String[] args) {
        System.out.println("Hello World!");
        
        SimpleDateFormat df = new SimpleDateFormat("EEEEEE", new Locale("es"));
        String diasSemana = df.format(new Date()).toUpperCase();
        
        System.out.println(diasSemana);
    }*/
}
