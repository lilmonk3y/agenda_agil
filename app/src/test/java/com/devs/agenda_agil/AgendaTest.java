package com.devs.agenda_agil;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AgendaTest {
    @Test
    public void crear_un_evento_lo_agrega_a_la_agenda(){
        Agenda agenda = new Agenda();
        Evento evento = new Evento();

        agenda.agregarEvento(evento);

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
        agenda.agregarEvento(evento);

        agenda.eliminar(evento);

        assertFalse(agenda.pertenece(evento));
    }


}
