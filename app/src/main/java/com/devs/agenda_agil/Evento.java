package com.devs.agenda_agil;

import java.util.Calendar;

class Evento {
    private Calendar fecha;

    public Evento(){ }

    public Evento(Calendar fecha) {
        this.fecha = fecha;
    }

    public Evento(Calendar date, Tarea tarea) {
        this.fecha = date;
    }

    public Calendar fecha() {
        return this.fecha;
    }
}
