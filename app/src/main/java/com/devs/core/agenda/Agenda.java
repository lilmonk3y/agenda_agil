package com.devs.core.agenda;

import com.devs.core.entity.DiaDeAgenda;
import com.devs.core.entity.Evento;
import com.devs.core.entity.EventoCiclico;
import com.devs.core.entity.Tarea;
import com.devs.core.entity.TareaPlanificada;
import com.devs.core.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Agenda {
    private List<Evento> eventos = new ArrayList<>();
    private List<Tarea> backlog = new ArrayList<>();
    private List<Tarea> historialTareas = new ArrayList<>();
    private List<TareaPlanificada> planificado = new ArrayList<>();
    private DateUtil dateSupplier;
    private List<EventoCiclico> reglasDeEventosCiclicos = new ArrayList<>();

    public Agenda(DateUtil dateSupplier) {
        this.dateSupplier = dateSupplier;
    }

    public Agenda() {
        this.dateSupplier = new DateUtil();
    }

    public void agregar(Evento evento) {
        this.eventos.add(evento);
    }

    public void eliminar(Evento evento) {
        assert eventos.contains(evento);

        this.eventos.remove(evento);
    }

    public void agregar(Tarea tarea) {
        this.backlog.add(tarea);
    }

    public void eliminar(Tarea tarea) {
        assert backlog.contains(tarea);

        this.backlog.remove(tarea);
    }

    public boolean pertenece(Evento evento) {
        return this.eventos.contains(evento);
    }

    public boolean pertenece(Tarea tarea) {
        return this.backlog.contains(tarea);
    }

    public List<Tarea> backlog() {
        Collections.sort(this.backlog);
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
        Calendar diaDeMañana = this.getTomorrowDate();
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

        for(EventoCiclico eventoCiclico : this.reglasDeEventosCiclicos){
            reglaParaDiasDeSemana(diaAMostrar, obligacionesDelDia, eventoCiclico);
            reglaParaDiasDeMes(diaAMostrar, obligacionesDelDia, eventoCiclico);
            reglaDeRepeticionAnual(diaAMostrar, obligacionesDelDia, eventoCiclico);
        }

        return obligacionesDelDia;
    }

    private void reglaDeRepeticionAnual(Calendar diaAMostrar, DiaDeAgenda obligacionesDelDia, EventoCiclico eventoCiclico) {
        int diaDeRepeticionAnual = eventoCiclico.getRepiteAnual()[0];
        int mesDeRepeticionAnual = eventoCiclico.getRepiteAnual()[1];
        if(esPosteriorA(diaAMostrar, eventoCiclico.getDiaDeInicio()) && (diaAMostrar.get(Calendar.DAY_OF_MONTH) == diaDeRepeticionAnual && diaAMostrar.get(Calendar.MONTH) == mesDeRepeticionAnual)){
            obligacionesDelDia.add(new Evento(eventoCiclico.getTitulo()));
        }
    }

    private void reglaParaDiasDeMes(Calendar diaAMostrar, DiaDeAgenda obligacionesDelDia, EventoCiclico eventoCiclico) {
        if(esPosteriorA(diaAMostrar, eventoCiclico.getDiaDeInicio()) && diaAMostrar.get(Calendar.DAY_OF_MONTH) == eventoCiclico.getRepiteMensual()){
            obligacionesDelDia.add(new Evento(eventoCiclico.getTitulo()));
        }
    }

    private void reglaParaDiasDeSemana(Calendar diaAMostrar, DiaDeAgenda obligacionesDelDia, EventoCiclico eventoCiclico) {
        for(int dia : eventoCiclico.getRepiteSemanal()){
            if(esPosteriorA(diaAMostrar, eventoCiclico.getDiaDeInicio()) && (diaAMostrar.get(Calendar.DAY_OF_WEEK) == dia)){
                obligacionesDelDia.add(new Evento(eventoCiclico.getTitulo()));
            }
        }
    }

    public static boolean esPosteriorA(Calendar diaAMostrar, Calendar diaDeCreacion) {
        return diaAMostrar.get(Calendar.YEAR) > diaDeCreacion.get(Calendar.YEAR) || (diaAMostrar.get(Calendar.YEAR) == diaDeCreacion.get(Calendar.YEAR) && diaAMostrar.get(Calendar.DAY_OF_YEAR) >= diaDeCreacion.get(Calendar.DAY_OF_YEAR) );
    }



    public void realizar(Evento evento) {
        assert this.eventos.contains(evento);

        this.eventos.remove(evento);
        Evento eventoRealizado = new Evento(evento);
        eventoRealizado.setRealizado(true);
        this.eventos.add(eventoRealizado);
    }

    public List<Evento> eventosRealizados() {
        List<Evento> eventos = new ArrayList<>();
        for(Evento evento : this.eventos){
            if( evento.realizado() ){
                eventos.add(evento);
            }
        }
        return eventos;
    }

    public List<Evento> eventos() {
        return this.eventos;
    }

    public void rePlanificar(Evento evento, Calendar nuevaFecha) {
        assert this.eventos.contains(evento);

        this.eventos.remove(evento);
        Evento eventoRePlanificado = new Evento(evento);
        eventoRePlanificado.setFecha(nuevaFecha);
        this.eventos.add(eventoRePlanificado);
    }

    public void agregar(EventoCiclico evento) {
        this.reglasDeEventosCiclicos.add(evento);
    }
}
