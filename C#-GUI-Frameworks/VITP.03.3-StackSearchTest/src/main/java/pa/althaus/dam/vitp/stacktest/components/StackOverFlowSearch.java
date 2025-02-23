/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pa.althaus.dam.vitp.stacktest.components;

import java.util.ArrayList;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author samuelaa
 */
public class StackOverFlowSearch extends javax.swing.JPanel implements Serializable {

    ArrayList<RegistroStack> lstRegistros = new ArrayList<>();

    /**
     * Creates new form StackOverflowSearch
     */
    public StackOverFlowSearch() {
        initComponents();
    }

    private void serviceSearch(String text) throws UnirestException {
        long toDate = System.currentTimeMillis() / 1000;
        long diferencia = 31560000L;
        long fromDate = toDate - diferencia;
        HttpResponse<JsonNode> jsonResponse
                = Unirest.get("https://api.stackexchange.com/2.3/search")
                        .header("accept", "application/json")
                        .queryString("page", "1")
                        .queryString("pagesize", "10")
                        .queryString("fromdate", fromDate)
                        .queryString("todate", toDate)
                        .queryString("order", "desc")
                        .queryString("sort", "creation")
                        .queryString("tagged", text)
                        .queryString("site", "stackoverflow")
                        .asJson();

        JSONObject jn = jsonResponse.getBody().getObject();
        JSONArray itemsArray = jn.getJSONArray("items");
        lstRegistros.clear();
        for (int x = 0; x < itemsArray.length(); x++) {
            JSONObject currentItem = itemsArray.getJSONObject(x);
            JSONObject owner = currentItem.getJSONObject("owner");
            RegistroStack currentRegStack = new RegistroStack(currentItem.getInt("question_id"),
                    new Date(currentItem.getLong("creation_date") * 1000),
                    currentItem.getString("title"),
                    owner.getString("display_name"),
                    currentItem.getBoolean("is_answered"),
                    currentItem.getString("link"));
            lstRegistros.add(currentRegStack);
        }
        StackTableModel tbModel = new StackTableModel(lstRegistros);
        this.tblResultados.setModel(tbModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        lblError = new javax.swing.JLabel();

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblResultados.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblResultados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblResultadosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblResultados);

        lblError.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setText("Error no Controlado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lblError)
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        if (txtBusqueda.getText().isBlank()) {
            lblError.setVisible(true);
            lblError.setText("Por favor, introduzca un patrón de búsqueda");
        } else if (txtBusqueda.getText().contains(" ")) {
            lblError.setVisible(true);
            lblError.setText("Por favor, introduzca únicamente una palabra");
        } else {
            try {
                serviceSearch(txtBusqueda.getText());
                lblError.setVisible(false);
            } catch (UnirestException ex) {
                Logger.getLogger(StackOverFlowSearch.class.getName()).log(Level.SEVERE, null, ex);
                lblError.setText("Error en petición al servicio: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblResultadosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultadosMousePressed
       JTable table = (JTable)evt.getSource();
       Point point = evt.getPoint();
       int row = table.rowAtPoint(point);
       if(evt.getClickCount() == 2 && table.getSelectedRow() != -1){
           RegistroStack rs = this.lstRegistros.get(row);
           DlgRegistroStack dlgRs = new DlgRegistroStack((JFrame)this.getTopLevelAncestor(), true, rs);
           dlgRs.setVisible(true);
       }
    }//GEN-LAST:event_tblResultadosMousePressed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            ActionEvent ev = null;
            btnBuscarActionPerformed(ev);
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblError;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
