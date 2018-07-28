package com.devs.agenda_agil;

import java.util.Calendar;
import java.util.Objects;

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

    public Evento(String titulo) {
        this.titulo = titulo;
        this.realizado = false;
        this.fecha = null;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(titulo, evento.titulo) &&
                Objects.equals(fecha, evento.fecha) &&
                Objects.equals(realizado, evento.realizado);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
