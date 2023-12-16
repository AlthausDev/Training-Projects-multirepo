/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package pa.althaus.dam.javaproyect.aeropuerto.controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static pa.althaus.dam.javaproyect.aeropuerto.util.Constants.FRAME_HEIGHT;
import static pa.althaus.dam.javaproyect.aeropuerto.util.Constants.FRAME_WIDTH;

public class HelpService {


    private JFXPanel jfxPanel;
    private JFrame frame;

    public HelpService() {
        jfxPanel = new JFXPanel();
        frame = new JFrame("Ayuda");
        frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.add(jfxPanel);
    }

    public void setHelp(JFrame frame, String helpUrl) {
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "Help");
        frame.getRootPane().getActionMap().put("Help", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebView(helpUrl);
            }
        });
    }

    public void openWebView(String url) {
        Platform.runLater(() -> {
            try {
                WebView webView = new WebView();
                WebEngine webEngine = webView.getEngine();

                webEngine.load(url);
                jfxPanel.setScene(new Scene(webView));

                SwingUtilities.invokeLater(() -> frame.setVisible(true));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
