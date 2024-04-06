package com.althaus.dev;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class AppLauncher {

    public static void main(String[] args) {

        try {
            String url = "jdbc:hsqldb:file:src/main/java/com/althaus/dev/test";
            String user = "SA";
            String password = "";
            Connection connection = null;
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection(url, user, password);

            Map<String, Object> parameters = new HashMap<String, Object>(); parameters.put("IDFACTURA",1);

            JasperPrint report = JasperFillManager.fillReport("src/main/java/com/althaus/dev/reports/Clientes.jasper", parameters, connection);

            JasperExportManager.exportReportToPdfFile(report, "src/main/java/com/althaus/dev/export/Clientes.pdf");

        } catch (ClassNotFoundException | SQLException | JRException e) {
            throw new RuntimeException(e);
        }
    }
}