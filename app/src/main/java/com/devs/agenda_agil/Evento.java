package com.devs.agenda_agil;

import java.util.Date;

class Evento {
    private Date fecha;

    public Evento(){
        this.fecha = new Date();
    }

    public Evento(Date fecha) {
        this.fecha = fecha;
    }

    public Date fecha() {
        return this.fecha;
    }

}
