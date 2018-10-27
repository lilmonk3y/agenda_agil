package com.devs.agenda_agil;

import com.devs.entity.Evento;
import com.devs.entity.TareaPlanificada;

import java.util.ArrayList;
import java.util.List;

class DiaDeAgenda {
    List<Evento> eventos = new ArrayList<>();
    List<TareaPlanificada> tareas = new ArrayList<>();

    public void add(Evento evento) {
        this.eventos.add(evento);
    }

    public void add(TareaPlanificada tarea) {
        this.tareas.add(tarea);
    }

    public List<Evento> eventos() {
        return this.eventos;
    }

    public List<TareaPlanificada> tareas() {
        return this.tareas;
    }
}
