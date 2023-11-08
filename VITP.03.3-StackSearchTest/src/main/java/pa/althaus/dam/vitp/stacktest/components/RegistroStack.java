/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.althaus.dam.vitp.stacktest.components;

import java.util.Date;

/**
 *
 * @author samuelaa
 */
public class RegistroStack {

    private int id;
    private Date fechaCreacion;
    private String titulo;
    private String autor;
    private boolean conRespuestas;
    private String url;


    public RegistroStack(int id, Date fechaCreacion, String titulo, String autor, boolean conRespuestas, String url) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.titulo = titulo;
        this.autor = autor;
        this.conRespuestas = conRespuestas;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isConRespuestas() {
        return conRespuestas;
    }

    public void setConRespuestas(boolean conRespuestas) {
        this.conRespuestas = conRespuestas;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }  

}
