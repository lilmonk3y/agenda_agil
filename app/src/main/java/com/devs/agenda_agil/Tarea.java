package com.devs.agenda_agil;

import android.support.annotation.NonNull;

class Tarea implements Comparable<Tarea>{
    private final Prioridad prioridad;
    private String nombre;

    public Tarea(String nombre) {
        this.nombre = nombre;
        prioridad = Prioridad.SIN_PRIORIZAR;
    }

    public Tarea() {
        prioridad = Prioridad.SIN_PRIORIZAR;
    }

    public Tarea(String nombre_de_la_tarea, Prioridad prioridad) {
        this.nombre = nombre_de_la_tarea;
        this.prioridad = prioridad;
    }

    public String nombre() {
        return this.nombre;
    }

    public int prioridad(){
        return this.prioridad.getNivel();
    }

    @Override
    public int compareTo(@NonNull Tarea otra) {
        if( this.prioridad() <= otra.prioridad()){
            return -1;
        }else{
            return 1;
        }
    }

}
