package com.devs.core.entity;

import com.devs.core.entity.Evento;
import com.devs.core.entity.TareaPlanificada;

import java.util.ArrayList;
import java.util.List;

public class DiaDeAgenda {
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
