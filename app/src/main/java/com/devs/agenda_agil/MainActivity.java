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
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Agenda agenda = new Agenda();
    Calendar fecha = new GregorianCalendar().getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFechaDeHoy();

        View agregarEventoTarea = findViewById(R.id.agregarEventoTarea);
        agregarEventoTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent crearEventoTareaIntent = new Intent(MainActivity.this, CrearEventoTareaActivity.class);
                startActivity(crearEventoTareaIntent);
            }
        });

        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad Maxima", Prioridad.MAXIMA));
        agenda.agregar(new Tarea("Tarea prioridad media", Prioridad.MEDIA));
        agenda.agregar(new Tarea("Tarea sin prioridad"));
        mostrarBacklog();
    }

    private void setFechaDeHoy() {
        int dia = this.fecha.get(Calendar.DAY_OF_MONTH);
        Locale locale = Locale.getDefault();
        String mes = this.fecha.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
        String diaDeLaSemana = this.fecha.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);

        TextView displayDia = findViewById(R.id.dia);
        displayDia.setText(Integer.toString(dia));

        TextView displayMes = findViewById(R.id.mes);
        displayMes.setText(mes);

        TextView displayDiaDeLaSemana = findViewById(R.id.dia_de_la_semana);
        displayDiaDeLaSemana.setText(diaDeLaSemana);
    }

    public void mostrarBacklog(){
        List<Tarea> tareas = agenda.backlog();
        TareasArrayAdapter arrayAdapter = new TareasArrayAdapter(this,(ArrayList<Tarea>) tareas);
        ListView listaDeTareas = (ListView) findViewById(R.id.list_tareas);
        listaDeTareas.setAdapter(arrayAdapter);
    }
}
