package com.devs.agenda_agil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Agenda agenda = new Agenda();
    Calendar fecha = new GregorianCalendar().getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFechaDeHoy();

        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.BAJA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.BAJA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.BAJA));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        mostrarBacklog(findViewById(R.id.backlogButton));
    }

    private void setFechaDeHoy() {
        int dia = this.fecha.get(Calendar.DAY_OF_MONTH);
        Locale locale = Locale.getDefault();
        String mes = this.fecha.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
        String diaDeLaSemana = this.fecha.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);

        TextView displayDia = (TextView) findViewById(R.id.dia);
        displayDia.setText(Integer.toString(dia));

        TextView displayMes = (TextView) findViewById(R.id.mes);
        displayMes.setText(mes);

        TextView displayDiaDeLaSemana = (TextView) findViewById(R.id.dia_de_la_semana);
        displayDiaDeLaSemana.setText(diaDeLaSemana);
    }

    public void mostrarBacklog(View view){
        ArrayList<Tarea> tareas = (ArrayList<Tarea>) agenda.backlog();
        TareasArrayAdapter arrayAdapter = new TareasArrayAdapter(this,tareas);
        ListView listaDeTareas = (ListView) findViewById(R.id.list_tareas);
        listaDeTareas.setAdapter(arrayAdapter);
    }

    public void mostrarEventos(View view){
        ArrayList<Tarea> tareas = (ArrayList<Tarea>) agenda.backlog();
        TareasArrayAdapter arrayAdapter = new TareasArrayAdapter(this,tareas);
        ListView listaDeTareas = (ListView) findViewById(R.id.list_tareas);
        listaDeTareas.setAdapter(arrayAdapter);
    }




//    mostrar backlog en otra activity
//    public void backlog(View view){
//        Intent intent = new Intent(this, BacklogActivity.class);
//        startActivity(intent);
//    }
}
