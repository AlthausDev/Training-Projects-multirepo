/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.cifaviles.dam.diact1_2;

/**
 *
 * @author samuelaa * 
 * Repositorio GitHub: https://github.com/AlthausDev/DDI/tree/master
 * https://github.com/AlthausDev/DDI/tree/master/src/main/java/pa/cifaviles/dam/diact1_2
 * 
 */
public class Principal {
    
    public static Alumno[] alumnos = new Alumno [5];
    public static int cont = 0;
    
    
    public static void main(String[] args) {        
        new FrmAlumno().setVisible(true);
    }
}
