/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.althaus.dam.javaproyect.aeropuerto.validator;

/**
 *
 * @author samuelaa
 */
public class Validador {

    public boolean prefixValid (int prefix) {
        return prefix <= 999;
    }

    public boolean comCodValid(String code) {
        
        return true;
    }
    
    public boolean tlfValid(String tlf){
        //2 partes, la primera un String de 3 digitos
        //la segunda <= 12 digitos, minimo ¿8?
        return true;
    }
    
    public boolean flyCodValid(String code){
        return true;
    }
    
    public boolean operatingDaysValid(){
            return true;
    }
    
    
}
