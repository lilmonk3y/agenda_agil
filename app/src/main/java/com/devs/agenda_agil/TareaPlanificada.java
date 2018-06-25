package com.devs.agenda_agil;

import java.util.Calendar;

class TareaPlanificada {
    private Calendar fecha;
    private String nombre;

    public TareaPlanificada(String nombre, Calendar fecha) {
        this.fecha = fecha;
        this.nombre = nombre;
    }

    public Calendar fecha() {
        return this.fecha;
    }

    public String nombre() {
        return this.nombre;
    }
}
