/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.althaus.dam.vitp.components;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author samuelaa
 */
public class DosColores implements Serializable {
    
   private Color colorFondo;
   private Color colorTexto;

   
    public DosColores(Color colorFondo, Color colorTexto) {
        this.colorFondo = colorFondo;
        this.colorTexto = colorTexto;
    } 
   

    public Color getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    public Color getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(Color colorTexto) {
        this.colorTexto = colorTexto;
    }
    
   
}
