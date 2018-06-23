package com.devs.agenda_agil;

import com.devs.Exception.NotImplemented;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Agenda {
    List<Evento> eventos = new ArrayList<>();
    List<Tarea> backlog = new ArrayList<>();
    List<Tarea> historialTareas = new ArrayList<>();
    List<Tarea> planificado = new ArrayList<>();

    public void agregar(Evento evento) {
        this.eventos.add(evento);
    }

    public boolean pertenece(Evento evento) {
        return this.eventos.contains(evento);
    }

    public void eliminar(Evento evento) {
        this.eventos.remove(evento);
    }

    public void agregar(Tarea tarea) {
        this.backlog.add(tarea);
    }

    public boolean pertenece(Tarea tarea) {
        return this.backlog.contains(tarea);
    }

    public List<Tarea> backlog() {
        return this.backlog;
    }

    public void realizar(Tarea tarea) {
        assert(this.backlog().contains(tarea));

        this.backlog.remove(tarea);
        this.historialTareas.add(tarea);
    }

    public List<Tarea> historialDeTareas() {
        return this.historialTareas;
    }

    /**
     * La utilidad de este método la vemos cuando en la aplicación tengamos la view
     * planificarDia llamemos a planificar()
     * @param tarea
     */
    public void planificar(Tarea tarea) {
        assert(this.backlog().contains(tarea));

        this.backlog.remove(tarea);
        this.planificado.add(tarea);
    }

    public boolean planificado(Tarea tarea) {
        return this.planificado.contains(tarea);
    }

    public List<Evento> mostrarDia(Date diaAMostrar) {
        List<Evento> eventosAMostrar = new ArrayList<>();
        for(Evento evento : this.eventos){
            if(evento.fecha() == diaAMostrar){
                eventosAMostrar.add(evento);
            }
        }
        return eventosAMostrar;
    }
}
