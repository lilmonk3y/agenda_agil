package com.devs.agenda_agil;

import android.support.annotation.NonNull;

import java.util.Calendar;

class Evento implements Comparable<Evento>{
    private String titulo;
    private Calendar fecha;
    private Boolean realizado;
    private int[] diasDeRepeticion;
    private int hashDeEvento;

    public Evento(){
        this.realizado = false;
        this.diasDeRepeticion = new int[]{};
        this.hashDeEvento = 0;
    }

    public Evento(Calendar fecha) {
        this.fecha = fecha;
        this.realizado = false;
        this.diasDeRepeticion = new int[]{};
        this.hashDeEvento = 0;
    }

    public Evento(Calendar date, Tarea tarea) {
        this.fecha = date;
        this.realizado = false;
        this.diasDeRepeticion = new int[]{};
        this.hashDeEvento = 0;
    }

    public Evento(Calendar fechaDeHoy, String titulo) {
        this.fecha = fechaDeHoy;
        this.titulo = titulo;
        this.realizado = false;
        this.diasDeRepeticion = new int[]{};
        this.hashDeEvento = 0;
    }

    public Evento(Evento otro) {
        this.realizado = otro.realizado();
        this.titulo = otro.titulo();
        this.fecha = otro.fecha();
        this.diasDeRepeticion = otro.diasDeRepeticion;
        this.hashDeEvento = otro.getHashDeEvento();
    }

    public Evento(Calendar fecha, String titulo, int[] diasDeRepeticion){
        this.fecha = fecha;
        this.titulo = titulo;
        realizado = false;
        this.diasDeRepeticion = diasDeRepeticion;
        this.hashDeEvento = 0;
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

    public int[] getDiasDeRepeticion() {
        return this.diasDeRepeticion;
    }

    public int getHashDeEvento() {
        return this.hashDeEvento;
    }

    public void setHashDeEvento(int i) {
        this.hashDeEvento = i;
    }

    @Override
    public int compareTo(@NonNull Evento otra) {
        if( this.getHashDeEvento() <= otra.getHashDeEvento()){
            return -1;
        } else {
            return 1;
        }
    }


}
