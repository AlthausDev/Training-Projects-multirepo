/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.althaus.dam.vitp.stacktest.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author samuelaa
 */
public class StackTableModel extends AbstractTableModel {

    List<RegistroStack> lstRegistros;
    ArrayList<String> columnNames;
    ArrayList<Class> columnClasses;

    public StackTableModel(List<RegistroStack> source) {
        this.lstRegistros = source;
        columnNames = new ArrayList<>();
        columnClasses = new ArrayList<>();
        columnNames.add("Identificador");
        columnNames.add("Fecha de creación");
        columnNames.add("Título");
        columnNames.add("Autor");
        columnNames.add("Con respuestas");
        columnClasses.add(Integer.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(Boolean.class);
    }

    @Override
    public int getRowCount() {
        return lstRegistros.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses.get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RegistroStack current = lstRegistros.get(rowIndex);
        if (current != null && columnIndex >= 0 && columnIndex <= 4) {
            switch (columnIndex) {
                case 0:
                    return current.getId();
                case 1:
                    SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    return fmt.format(current.getFechaCreacion());
                case 2:
                    return current.getTitulo();
                case 3:
                    return current.getAutor();
                case 4:
                    return current.isConRespuestas();
                default:
                    return "";
            }
        } else {
            return "";
        }
    }

}
