package com.devs.agenda_agil;

import java.util.ArrayList;
import java.util.List;

class Agenda {
    List<Evento> eventos = new ArrayList<>();

    public void agregarEvento(Evento evento) {
        this.eventos.add(evento);
    }

    public boolean pertenece(Evento evento) {
        return this.eventos.contains(evento);
    }

    public void eliminar(Evento evento) {
        this.eventos.remove(evento);
    }
}
