package com.devs.agenda_agil;

class Tarea {
    private String nombre;

    public Tarea(String nombre) {
        this.nombre = nombre;
    }

    public Tarea() { }

    public Tarea(String nombre_de_la_tarea, Prioridad prioridad) {

    }

    public String nombre() {
        return this.nombre;
    }
}
