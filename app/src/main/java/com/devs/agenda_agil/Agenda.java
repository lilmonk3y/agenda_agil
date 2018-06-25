package com.devs.agenda_agil;

import com.devs.src.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class Agenda {
    List<Evento> eventos = new ArrayList<>();
    List<Tarea> backlog = new ArrayList<>();
    List<Tarea> historialTareas = new ArrayList<>();
    List<TareaPlanificada> planificado = new ArrayList<>();
    DateUtil dateSupplier = new DateUtil();

    public Agenda(DateUtil dateSupplier) {
        this.dateSupplier = dateSupplier;
    }

    public Agenda() { }

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
        Calendar diaDeMañana = getTomorrowDate();
        TareaPlanificada tareaPlanificada = new TareaPlanificada(tarea.nombre(), diaDeMañana);
        this.planificado.add(tareaPlanificada);
    }

    private Calendar getTomorrowDate() {
        Calendar diaDeMañana = this.dateSupplier.getDate();
        diaDeMañana.add(Calendar.DATE, 1);
        return diaDeMañana;
    }

    public boolean planificada(Tarea tarea) {
        boolean pertenece = false;
        for(TareaPlanificada planificada : this.planificado){
            if(planificada.nombre().equals(tarea.nombre())){
                pertenece = true;
            }
        }
        return pertenece;
    }

    public DiaDeAgenda mostrarDia(Calendar diaAMostrar) {
        DiaDeAgenda obligacionesDelDia = new DiaDeAgenda();

        for(Evento evento : this.eventos){
            if( evento.fecha().equals(diaAMostrar) ){
                obligacionesDelDia.add(evento);
            }
        }

        for(TareaPlanificada tarea : this.planificado){
            if( tarea.fecha().equals(diaAMostrar) ){
                obligacionesDelDia.add(tarea);
            }
        }

        return obligacionesDelDia;
    }
}
