package com.devs.agenda_agil;

public enum Prioridad {
    MAXIMA (1),
    MEDIA(2),
    BAJA (3),
    SIN_PRIORIZAR (4);

    private final int nivel;

    Prioridad(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel(){
        return this.nivel;
    }
}
