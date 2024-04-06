/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.althaus.dam.vitp.components;

import javax.swing.JTextField;
import java.io.Serializable;
import javax.accessibility.AccessibleContext;
import javax.swing.event.DocumentEvent;

import javax.swing.event.DocumentListener;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author samuelaa
 */
public class ComponenteEjemplo extends JTextField implements Serializable {

    private DosColores colores;
    private int numCaracteres;
    private DosColores coloresDefecto;

    public ComponenteEjemplo() {
        //super();
        coloresDefecto = new DosColores(getBackground(), getForeground());
        super.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                analizaContenido();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                analizaContenido();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                analizaContenido();
            }

            private void analizaContenido() {
                if (getText().length() >= numCaracteres) {
                    setBackground(colores.getColorFondo());
                    setForeground(colores.getColorTexto());
                } else {
                    setBackground(coloresDefecto.getColorFondo());
                    setForeground(coloresDefecto.getColorTexto());
                }

            }
        });

    }

    public DosColores getColores() {
        return colores;
    }

    public void setColores(DosColores colores) {
        this.colores = colores;
    }

    public int getNumCaracteres() {
        return numCaracteres;
    }

    public void setNumCaracteres(int numCaracteres) {
        this.numCaracteres = numCaracteres;
    }

    public ComponentUI getUi() {
        return ui;
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public AccessibleContext getAccessibleContext() {
        return accessibleContext;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }
}
