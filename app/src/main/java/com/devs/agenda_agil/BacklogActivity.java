package com.devs.agenda_agil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BacklogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backlog);

        Agenda agenda = new Agenda();
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        ArrayList<Tarea> tareas = (ArrayList<Tarea>) agenda.backlog();

        TareasArrayAdapter arrayAdapter = new TareasArrayAdapter(this,tareas);
        ListView listaDeTareas = (ListView) findViewById(R.id.list_tareas);
        listaDeTareas.setAdapter(arrayAdapter);
    }
}
