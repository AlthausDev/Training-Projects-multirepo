/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.althaus.dam.vitp.components;

import java.io.Serializable;
import javax.swing.JPanel;

/**
 *
 * @author S.Althaus
 */
public class JPanelImagen extends JPanel implements Serializable {

    private String rutaImagen;
    
    public JPanelImagen() {
        
    }   

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }   
    
}
