/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.althaus.dam.vitp.components;
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author samuelaa
 */
public class ColoresPropertiesEditorSuport extends PropertyEditorSupport {
    private ColoresPanel coloresPanel = new ColoresPanel();

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public Component getCustomEditor() {
        return coloresPanel;
    }

    @Override
    public String getJavaInitializationString() {
        Color colorFondo = coloresPanel.getPropiedadSeleccionada().getColorFondo();
        Color colorTexto = coloresPanel.getPropiedadSeleccionada().getColorTexto();
        return "new pa.althaus.dam.vitp.components.DosColores(new java.awt.color("+ 
                colorFondo.getRGB() + "), (" +  "new java.awt.Color(" + 
                colorTexto.getRGB() + ")";
    }

    @Override
    public Object getValue() {
        return coloresPanel.getPropiedadSeleccionada();
    }
    
    
    
}
