package com.devs.agenda_agil;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AgendaTest {
    @Test
    public void crear_un_evento_lo_agrega_a_la_agenda(){
        Agenda agenda = new Agenda();
        Evento evento = new Evento();

        agenda.agregar(evento);

        assertTrue(agenda.pertenece(evento));
    }

    @Test
    public void pregunto_por_un_evento_que_no_existe(){
        Agenda agenda = new Agenda();
        Evento evento = new Evento();

        assertFalse(agenda.pertenece(evento));
    }

    @Test
    public void elinimar_evento_de_la_agenda_lo_quita(){
        Agenda agenda = new Agenda();
        Evento evento = new Evento();
        agenda.agregar(evento);

        agenda.eliminar(evento);

        assertFalse(agenda.pertenece(evento));
    }

    @Test
    public void se_agregan_tareas_al_backlog_y_estas_aparecen_en_la_agenda(){
        Agenda agenda = new Agenda();
        Tarea tarea = new Tarea();

        agenda.agregar(tarea);

        assertTrue(agenda.pertenece(tarea));
    }

    @Test
    public void pregunto_si_la_tarea_existe_sin_haberla_agregado(){
        Agenda agenda = new Agenda();
        Tarea tarea = new Tarea();

        assertFalse(agenda.pertenece(tarea));
    }

    @Test
    public void agrego_tareas_a_la_agenda_y_esta_me_las_muestra(){
        Agenda agenda = new Agenda();
        Tarea tarea1 = new Tarea();
        Tarea tarea2 = new Tarea();

        agenda.agregar(tarea1);
        agenda.agregar(tarea2);
        List<Tarea> tareas = agenda.backlog();

        assertTrue(tareas.contains(tarea1));
        assertTrue(tareas.contains(tarea2));
    }

    @Test
    public void completo_una_tarea_que_esta_en_el_backlog_y_esta_se_agrega_al_historial_de_tareas_realizadas(){
        Agenda agenda = new Agenda();
        Tarea tarea = new Tarea();
        agenda.agregar(tarea);

        agenda.realizar(tarea);

        assertTrue(agenda.historialDeTareas().contains(tarea));
    }

    @Test
    public void planifico_una_tarea_del_backlog_y_esta_se_agrega_para_el_siguiente_dia(){
        Agenda agenda = new Agenda();
        Tarea tarea = new Tarea();
        agenda.agregar(tarea);

        agenda.planificar(tarea);

        assertTrue(agenda.planificado(tarea));
        assertFalse(agenda.pertenece(tarea));
    }

    //TODO: MIRAR BIEN ESTE TEST QUE CREO QUE ESTÁ MAL
    @Test
    public void agregamos_eventos_a_un_dia_y_estos_se_muestran(){
        // Capaz useamos mejor la clase gregorian calendar.
        Date diaAMostrar = new Date(6,1,2018);
        Date diaIncorecto = new Date(5,1,2018);
        Agenda agenda = new Agenda();
        Evento evento = new Evento(diaAMostrar);
        Evento eventoQueNoSeDebeMostrar = new Evento(diaIncorecto);

        agenda.agregar(evento);
        agenda.agregar(eventoQueNoSeDebeMostrar);
        List<Evento> eventosDelDiaAMostrar = agenda.mostrarDia(diaAMostrar);

        assertTrue(eventosDelDiaAMostrar.contains(evento));
        assertFalse(eventosDelDiaAMostrar.contains(eventoQueNoSeDebeMostrar));
    }

    // TODO: HACER EL TEST PARA QUE AL PLANIFICAR UNA TAREA ESTA SE AGREGUE A LOS EVENTOS DEL DÍA SIGUIENTE
    @Test
    public void test(){
        Agenda agenda = new Agenda();
        //agrego y planifico tarea
        Tarea tarea = new Tarea();
        agenda.agregar(tarea);
        agenda.planificar(tarea);
    }

    /*
    agenda.finalizarDia
    */
}

