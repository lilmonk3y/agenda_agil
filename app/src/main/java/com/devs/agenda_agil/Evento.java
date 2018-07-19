package com.devs.agenda_agil;

import java.lang.reflect.Array;
import java.util.Calendar;

class Evento {
    private String titulo;
    private Calendar fecha;
    private Boolean realizado;

    public Evento(){
        this.realizado = false;
    }

    public Evento(Calendar fecha) {
        this.fecha = fecha;
        this.realizado = false;
    }

    public Evento(Calendar date, Tarea tarea) {
        this.fecha = date;
        this.realizado = false;
    }

    public Evento(Calendar fechaDeHoy, String titulo) {
        this.fecha = fechaDeHoy;
        this.titulo = titulo;
        this.realizado = false;
    }

    public Evento(Evento otro) {
        this.realizado = otro.realizado();
        this.titulo = otro.titulo();
        this.fecha = otro.fecha();
    }

    public String titulo() {
        return this.titulo;
    }

    public Calendar fecha() {
        return this.fecha;
    }

    public boolean realizado() {
        return this.realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
}
