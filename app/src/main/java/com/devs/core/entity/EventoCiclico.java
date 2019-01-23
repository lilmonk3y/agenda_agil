package com.devs.core.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventoCiclico {
    private String titulo;
    private Calendar diaDeInicio;
    private List<Integer> repiteSemanal = new ArrayList<>();
    private int repiteMensual;
    private int[] repiteAnual = new int[]{0,0};

    public EventoCiclico(String titulo, Calendar fechaDeInicio) {
        this.titulo = titulo;
        this.diaDeInicio = fechaDeInicio;
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

    public void agregarRepeticionAnual(int dia, int mes) {
        this.repiteAnual = new int[]{dia, mes};
    }

    public int[] getRepiteAnual() {
        return repiteAnual;
    }
}
