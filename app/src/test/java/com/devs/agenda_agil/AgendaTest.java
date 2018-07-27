package com.devs.agenda_agil;

import android.support.annotation.NonNull;

import com.devs.src.DateUtil;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
    public void elimino_una_tarea_del_backlog_y_esta_se_elimina(){
        Agenda agenda = new Agenda();
        Tarea tarea = new Tarea();
        agenda.agregar(tarea);

        agenda.eliminar(tarea);

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
        Tarea tarea = new Tarea("Comprar pan.");
        agenda.agregar(tarea);

        agenda.planificar(tarea);

        assertTrue(agenda.planificada(tarea));
        assertFalse(agenda.pertenece(tarea));
    }

    @Test
    public void agregamos_eventos_a_un_dia_y_estos_se_muestran(){
        Calendar diaAMostrar = new GregorianCalendar(2018,1,1);
        Calendar diaIncorrecto = new GregorianCalendar(2018,4,5);
        Agenda agenda = new Agenda();
        Evento evento = new Evento(diaAMostrar);
        Evento eventoQueNoSeDebeMostrar = new Evento(diaIncorrecto);

        agenda.agregar(evento);
        agenda.agregar(eventoQueNoSeDebeMostrar);
        DiaDeAgenda DiaAMostrar = agenda.mostrarDia(diaAMostrar);

        assertTrue(DiaAMostrar.eventos().contains(evento));
        assertFalse(DiaAMostrar.eventos().contains(eventoQueNoSeDebeMostrar));
    }

    @Test
    public void al_planificar_una_tarea_esta_se_agrega_a_los_eventos_del_dia_siguiente(){
        Calendar fechaDeHoy = new GregorianCalendar(2017,12,15);
        final DateUtil dateSupplier = Mockito.mock(DateUtil.class);
        Mockito.when(dateSupplier.getDate()).thenReturn(fechaDeHoy);
        Agenda agenda = new Agenda(dateSupplier);
        Tarea tarea = new Tarea();
        agenda.agregar(tarea);

        agenda.planificar(tarea);

        Calendar fechaDeMañana = getNextDate(fechaDeHoy);
        //Calendar fechaDeMañana = DateUtil.newDateAddingDays(fechaDeHoy,1);
        DiaDeAgenda DiaAMostrar = agenda.mostrarDia(fechaDeMañana);
        assertTrue(DiaAMostrar.tareas().size() == 1);
    }

    @Test
    public void le_pido_el_backlog_a_la_agenda_y_este_debe_estar_ordenado() {
        Agenda agenda = new Agenda();
        Tarea tareaMaxima = new Tarea("nombre de la tarea", Prioridad.MAXIMA);
        Tarea tareaBaja = new Tarea("nombre de la manteca", Prioridad.BAJA);
        Tarea tareaSinPrioridad = new Tarea("nombre de tarea sin getNivel");
        agenda.agregar(tareaMaxima);
        agenda.agregar(tareaSinPrioridad);
        agenda.agregar(tareaBaja);

        List<Tarea> backlog = agenda.backlog();

        assertTrue(tareaMaxima == backlog.get(0));
        assertTrue(tareaBaja == backlog.get(1));
        assertTrue(tareaSinPrioridad == backlog.get(2));
    }

    @Test
    public void realizar_un_evento_planificado_para_el_día_lo_debe_marcar_como_realizado(){
        Calendar fechaDeHoy = new GregorianCalendar(2018,7,10);
        final DateUtil dateSupplier = Mockito.mock(DateUtil.class);
        Mockito.when(dateSupplier.getDate()).thenReturn(fechaDeHoy);
        Agenda agenda = new Agenda(dateSupplier);

        Evento eventoQueSeDebeMostrar = new Evento(fechaDeHoy, "Titulo");
        agenda.agregar(eventoQueSeDebeMostrar);

        Calendar otraFecha = new GregorianCalendar(2018,6,15);
        Evento eventoNoMostrable = new Evento(otraFecha, "Titulo otro");
        agenda.agregar(eventoNoMostrable);


        agenda.realizar(eventoQueSeDebeMostrar);

        assertEquals(2,agenda.eventos().size());
        assertTrue(agenda.eventos().contains(eventoNoMostrable));
        assertFalse(agenda.eventosRealizados().contains(eventoNoMostrable));
        assertFalse(agenda.eventos().contains(eventoQueSeDebeMostrar));
        List<Evento> eventosRealizados = agenda.eventosRealizados();
        assertEquals(1,agenda.eventosRealizados().size());
        Evento eventoRealizado = eventosRealizados.get(0);
        assertEquals(eventoQueSeDebeMostrar.titulo(), eventoRealizado.titulo() );
        assertEquals(eventoQueSeDebeMostrar.fecha(), eventoRealizado.fecha() );
    }

    @Test
    public void un_evento_que_no_se_realizó_lo_queremos_re_planificar(){
        Calendar fechaDeHoy = new GregorianCalendar(2018,7,10);
        final DateUtil dateSupplier = Mockito.mock(DateUtil.class);
        Mockito.when(dateSupplier.getDate()).thenReturn(fechaDeHoy);
        Agenda agenda = new Agenda(dateSupplier);

        Evento evento = new Evento(fechaDeHoy, "Titulo");
        agenda.agregar(evento);


        Calendar nuevaFecha = new GregorianCalendar(2018,8,10);
        agenda.rePlanificar(evento, nuevaFecha);

        assertEquals(1, agenda.eventos().size());
        Evento expected = agenda.eventos().get(0);
        assertEquals(evento.titulo(),expected.titulo());
        assertEquals(nuevaFecha, expected.fecha());
    }

//    @Test
//    public void eventos_ciclicos(){
//        Calendar fechaDeHoy = new GregorianCalendar(2018,7,7);
//        final DateUtil dateSupplier = Mockito.mock(DateUtil.class);
//        Mockito.when(dateSupplier.getDate()).thenReturn(fechaDeHoy);
//        Agenda agenda = new Agenda(dateSupplier);
//
//
//        EventoCiclico evento = new EventoCiclico("titulo");
//        evento.agregarRepeticion(Calendar.MONDAY);
//        evento.agregarRepeticion(Calendar.FRIDAY);
//
//
//        Calendar expectedMonday = new GregorianCalendar(2018,7,9);
//        Calendar expectedFriday = new GregorianCalendar(2018,7,13);
//
//        Calendar expectedMondayPlusOneWeek = new GregorianCalendar(2018,7,9);
//        expectedMondayPlusOneWeek.add(Calendar.DATE,Calendar.DAY_OF_WEEK);
//
//        Calendar expectedFridayPlusOneWeek = new GregorianCalendar(2018,7,13);
//        expectedFridayPlusOneWeek.add(Calendar.DATE,Calendar.DAY_OF_WEEK);
//
//        assertTrue(agenda.mostrarDia(expectedMonday).eventos().contains(evento));
//        assertTrue(agenda.mostrarDia(expectedFriday).eventos().contains(evento));
//        assertTrue(agenda.mostrarDia(expectedMondayPlusOneWeek).eventos().contains(evento));
//
//        Calendar previousMondayOfCreationDate = new GregorianCalendar(2018,7,7);
//        previousMondayOfCreationDate.add(Calendar.DATE,-Calendar.DAY_OF_WEEK);
//
//        assertFalse(agenda.mostrarDia(previousMondayOfCreationDate).eventos().contains(evento));
//    }

    @Test public void agrego_un_evento_ciclico(){
        Calendar fecha = new GregorianCalendar().getInstance();
        Calendar fechaUnaSemanaAntesDelEvento = new GregorianCalendar(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH),fecha.get(Calendar.DAY_OF_MONTH));
        fechaUnaSemanaAntesDelEvento.add(Calendar.WEEK_OF_MONTH,-1);
        Agenda agenda = new Agenda();

        int[] diasDeRepeticion = {Calendar.MONDAY, Calendar.THURSDAY, Calendar.SATURDAY};
        Tiempo periodoEntreRepeticiones = Tiempo.SEMANA;
        Tiempo.Intervalo intervaloDelPeriodo = Tiempo.Intervalo.TRES;
        Calendar fechaFinalDeRepeticiones = new GregorianCalendar(fecha.get(Calendar.YEAR)+1, fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH));

        EventoCiclico eventoCiclico01 = new EventoCiclico(fecha, fechaFinalDeRepeticiones, "titulo", periodoEntreRepeticiones, intervaloDelPeriodo);
        agenda.agregar(eventoCiclico01);
        EventoCiclico eventoCiclico02 = new EventoCiclico(fecha, fechaFinalDeRepeticiones, "titulo", diasDeRepeticion);

        int dia_del_año_del_en_el_que_debe_haber_un_evento = agenda.mostrarDia(fecha).eventos().size();
        int dia_del_año_en_el_que_no_debe_haber_eventos = agenda.mostrarDia(fechaUnaSemanaAntesDelEvento).eventos().size();

        assertTrue(agenda.eventosCiclicos().contains(eventoCiclico01));
        assertEquals(1, dia_del_año_del_en_el_que_debe_haber_un_evento);
        assertEquals(0, dia_del_año_en_el_que_no_debe_haber_eventos);
    }


    /*
    Pendientes:
    TODO: Necesitamos que las listas se guarden en una base de datos.
    TODO: (Más adelante) Al momento de finalizar un evento debemos preguntar al usuario si lo realizó y
    eventualmente tomar acciones sobre ello como por ejemplo preguntar por medio de notificaciones.
    TODO: Tenemos que hacer el manejo de dependencias. (Guice)
    (3) TODO: Crear eventos cíclicos, osea eventos que se repiten distintos días en el mismo horario.
    TODO: (Versión futura) Tener distintos calendarios en una misma agenda. Un ejemplo es que
    tengamos un calendario de remedioS, otro de trabajo, otro personal, o mostrar todos.

    REALIZADOS:
    DONE: Tenemos que hacer el método terminar día, que lo que tiene que hacer es: mostrar todos los
    eventos y tareas que teniamos planificados para el ese día y pedirle al usuario que informe
    si los realizó o si deben ir de nuevo al backlog. (TerminarDía)
    DONE: Planificar las responsabilidades del día siguiente. (PlanificarDía)
    */


    //*  ------------------------- MÉTODOS AUXILIARES ------------------------------*//

    @NonNull
    private Calendar getNextDate(Calendar fechaDeHoy) {
        fechaDeHoy.add(Calendar.DATE, 1);
        return fechaDeHoy;
    }
}

