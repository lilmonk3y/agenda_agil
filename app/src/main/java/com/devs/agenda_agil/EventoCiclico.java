package com.devs.agenda_agil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class EventoCiclico {
    private String titulo;
    private Calendar diaDeInicio;
    private List<Integer> repiteSemanal;
    private int repiteMensual;

    public EventoCiclico(String titulo) {
        this.titulo = titulo;
        this.repiteSemanal = new ArrayList<>();
        this.diaDeInicio = null;
        this.repiteMensual = 0;
    }

    public EventoCiclico(String titulo, Calendar diaDeCreacion, List<Integer> repite) {
        this.titulo = titulo;
        this.diaDeInicio  = diaDeCreacion;
        this.repiteSemanal = repite;
        this.repiteMensual = 0;
    }

    public EventoCiclico(String titulo, Calendar date, List<Integer> repiteSemanal, int repiteMensual) {
        this.titulo = titulo;
        this.diaDeInicio  = date;
        this.repiteSemanal = repiteSemanal;
        this.repiteMensual = repiteMensual;
    }

    public String getTitulo() {
        return titulo;
    }


    public List<Integer> getRepiteSemanal() {
        return repiteSemanal;
    }


    public void agregarRepeticion(int dia) {
        this.repiteSemanal.add(dia);
    }


    public Calendar getDiaDeInicio() {
        return diaDeInicio;
    }

    public void agregarRepeticionMensual(int dia) {
        this.repiteMensual = dia;
    }

    public int getRepiteMensual() {
        return repiteMensual;
    }
}
