/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.cifaviles.dam.diact1_2;

/**
 *
 * @author samuelaa
 */
public class Principal {
    
    public static Alumno[] alumnos = new Alumno [5];
    public static int cont = 0;
    
    
    public static void main(String[] args) {        
        new FrmAlumno().setVisible(true);
    }
}
