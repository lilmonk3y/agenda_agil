package com.devs.agenda_agil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TareaTest {


    @Test
    public void agrego_tareas_con_prioridad_y_el_comparador_de_tareas_me_da_el_valor_esperado(){
        List<Tarea> tareas = new ArrayList<>();
        tareas.add(new Tarea("bla",Prioridad.MAXIMA));
        tareas.add(new Tarea("bla",Prioridad.MAXIMA));
        tareas.add(new Tarea("bla",Prioridad.SIN_PRIORIZAR));
        tareas.add(new Tarea("bla",Prioridad.BAJA ));
        tareas.add(new Tarea("bla",Prioridad.MEDIA ));
        tareas.add(new Tarea("bla",Prioridad.MEDIA ));
        tareas.add(new Tarea("bla",Prioridad.MAXIMA ));
        tareas.add(new Tarea("bla",Prioridad.SIN_PRIORIZAR ));
        tareas.add(new Tarea("bla",Prioridad.BAJA ));

        Collections.sort(tareas);

        assertTrue(this.estarOrdenada(tareas));
    }

    private boolean estarOrdenada(List<Tarea> tareas) {
        Boolean respuesta = true;
        for(int indice = 0; indice < tareas.size()-1; indice++){
            if(tareas.get(indice).prioridad() > tareas.get(indice+1).prioridad()){
                respuesta = false;
            }
        }
        return respuesta;
    }
}
