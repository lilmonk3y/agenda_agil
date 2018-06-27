package com.devs.agenda_agil;

public enum Prioridad {
    MAXIMA (1),
    BAJA (2),
    SIN_PRIORIZAR (3);

    private final int nivel;

    Prioridad(int nivel) {
        this.nivel = nivel;
    }
}
