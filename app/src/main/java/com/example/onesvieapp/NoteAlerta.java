package com.example.onesvieapp;

import com.google.firebase.firestore.GeoPoint;

import java.util.Date;

public class NoteAlerta {
    private double latitud;
    private double longitud;
    private String descripcion;
    private String profundidad;
    private String magnitud;
    private Date fechahora;

    public NoteAlerta(){

    }
    public NoteAlerta(String descripcion, String profundidad, String magnitud, Date fechahora, double latitud, double longitud){
        this.descripcion = descripcion;
        this.profundidad = profundidad;
        this.magnitud = magnitud;
        this.fechahora = fechahora;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getProfundidad() {
        return profundidad;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getLatitud(){
        return latitud;
    }
}
