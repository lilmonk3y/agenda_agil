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

    @Test
    public void agregoUnEventoCiclicoParaLunesYViernes_esperoQueTodosLosLunesYViernesMuestrenAlEventoEnSusObligaciones(){
        Calendar fechaDeHoy = new GregorianCalendar(2018,7,2);
        final DateUtil dateSupplier = Mockito.mock(DateUtil.class);
        Mockito.when(dateSupplier.getDate()).thenReturn(fechaDeHoy);
        Agenda agenda = new Agenda(dateSupplier);

        EventoCiclico evento = new EventoCiclico("titulo");
        evento.agregarRepeticion(Calendar.MONDAY);
        evento.agregarRepeticion(Calendar.FRIDAY);
        agenda.agregar(evento);

        Calendar expectedMonday = new GregorianCalendar(2018,7,6);
        Calendar expectedFriday = new GregorianCalendar(2018,7,10);
        Calendar expectedMondayPlusOneWeek = new GregorianCalendar(2018,7,13);
        expectedMondayPlusOneWeek.add(Calendar.DATE,Calendar.DAY_OF_WEEK);
        Evento expectedEvento = new Evento("titulo");
        List<Evento> expectedEventos = agenda.mostrarDia(expectedMonday).eventos();
        assertTrue(expectedEventos.contains(expectedEvento));
        assertTrue(agenda.mostrarDia(expectedFriday).eventos().contains(expectedEvento));
        assertTrue(agenda.mostrarDia(expectedMondayPlusOneWeek).eventos().contains(expectedEvento));

    }

    @Test
    public void agregoUnEventoCiclicoConRepeticionesEnLunesYViernes_esperoQueLosLunesYViernesPreviosALaCreacionDelEventoNoTenganAlEventoEnSusTareas(){
        Calendar fechaDeHoy = new GregorianCalendar(2018,7,25);
        final DateUtil dateSupplier = Mockito.mock(DateUtil.class);
        Mockito.when(dateSupplier.getDate()).thenReturn(fechaDeHoy);
        Agenda agenda = new Agenda(dateSupplier);

        EventoCiclico evento = new EventoCiclico("titulo");
        evento.agregarRepeticion(Calendar.MONDAY);
        evento.agregarRepeticion(Calendar.FRIDAY);
        agenda.agregar(evento);

        Calendar expectedPreviousMonday = new GregorianCalendar(2018,7,20);
        Calendar expectedPreviousFriday = new GregorianCalendar(2018,7,24);
        Evento expectedEvento = new Evento("titulo");
        List<Evento> expectedEventos = agenda.mostrarDia(expectedPreviousMonday).eventos();
        assertFalse(expectedEventos.contains(expectedEvento));
        assertFalse(agenda.mostrarDia(expectedPreviousFriday).eventos().contains(expectedEvento));
    }

    @Test
    public void test_comparar_fechas(){
        Calendar fechaDeHoy = new GregorianCalendar(2018,7,25);


        Calendar expectedDay = new GregorianCalendar(2018,7,24);
        assertTrue(Agenda.esPosteriorA(fechaDeHoy,expectedDay));
    }

    @Test
    public void test_comparar_fechas_2(){
        Calendar fechaDeHoy = new GregorianCalendar(2019,0,1);

        Calendar expectedDay = new GregorianCalendar(2018,7,20);
        assertTrue(Agenda.esPosteriorA(fechaDeHoy,expectedDay));
    }

    @Test
    public void agregamosRepeticionesParaUnDiaEspecificoDelMes_esteEventoSeMuestraEnLasObligacionesDelDia(){
        Calendar fechaDeHoy = new GregorianCalendar(2018,7,10);
        final DateUtil dateSupplier = Mockito.mock(DateUtil.class);
        Mockito.when(dateSupplier.getDate()).thenReturn(fechaDeHoy);
        Agenda agenda = new Agenda(dateSupplier);

        EventoCiclico evento = new EventoCiclico("titulo");
        evento.agregarRepeticionMensual( 15);
        agenda.agregar(evento);

        Calendar expectedDay = new GregorianCalendar(2018,7,15);
        Calendar expectedDayPlusAMonth = new GregorianCalendar(2018,8,15);
        Evento expectedEvento = new Evento("titulo");
        List<Evento> expectedEventos = agenda.mostrarDia(expectedDay).eventos();
        assertTrue(expectedEventos.contains(expectedEvento));
        assertTrue(agenda.mostrarDia(expectedDayPlusAMonth).eventos().contains(expectedEvento));
    }

    @Test
    public void agregamosRepeticionesParaUnDiaEspecificoDelMes_esteEventoNoSeDebeMostrarLosDiasPreviosALaCreacionDelEvento(){
        Calendar fechaDeHoy = new GregorianCalendar(2018,7,10);
        final DateUtil dateSupplier = Mockito.mock(DateUtil.class);
        Mockito.when(dateSupplier.getDate()).thenReturn(fechaDeHoy);
        Agenda agenda = new Agenda(dateSupplier);

        EventoCiclico evento = new EventoCiclico("titulo");
        evento.agregarRepeticionMensual( 15);
        agenda.agregar(evento);

        Calendar expectedPreviousDay = new GregorianCalendar(2018,6,15);
        Evento expectedEvento = new Evento("titulo");
        List<Evento> expectedEventos = agenda.mostrarDia(expectedPreviousDay).eventos();
        assertFalse(expectedEventos.contains(expectedEvento));
    }

    /*
    Pendientes:
    TODO: Necesitamos que las listas se guarden en una base de datos.
    TODO: (Más adelante) Al momento de finalizar un evento debemos preguntar al usuario si lo realizó y
    eventualmente tomar acciones sobre ello como por ejemplo preguntar por medio de notificaciones.
    TODO: Tenemos que hacer el manejo de dependencias. (Guice)
    TODO: (Versión futura) Tener distintos calendarios en una misma agenda. Un ejemplo es que
    tengamos un calendario de remedioS, otro de trabajo, otro personal, o mostrar todos.

    REALIZADOS:
    DONE: Tenemos que hacer el método terminar día, que lo que tiene que hacer es: mostrar todos los
    eventos y tareas que teniamos planificados para el ese día y pedirle al usuario que informe
    si los realizó o si deben ir de nuevo al backlog. (TerminarDía)
    DONE: Planificar las responsabilidades del día siguiente. (PlanificarDía)
    DONE: Crear eventos cíclicos, osea eventos que se repiten distintos días en el mismo horario.
    */


    //*  ------------------------- MÉTODOS AUXILIARES ------------------------------*//

    @NonNull
    private Calendar getNextDate(Calendar fechaDeHoy) {
        fechaDeHoy.add(Calendar.DATE, 1);
        return fechaDeHoy;
    }




}

