package com.devs.agenda_agil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Scene;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    Agenda agenda = new Agenda();
    Calendar fecha = new GregorianCalendar().getInstance();
    private ArrayList<Tarea> backlog = (ArrayList<Tarea>)  agenda.backlog();
    private ArrayList<Evento> eventos = (ArrayList<Evento>)  agenda.eventos();
    Bundle bundle = new Bundle();
    FloatingActionMenu fabMenu;
    FloatingActionButton fabEventos;
    FloatingActionButton fabTareas;
    String creadorDeTareasNombre;
    int creadorDeTareasPrioridad;
    Tarea tarea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        BottomNavigationView barraDeNavegacion = findViewById(R.id.barra_de_navegacion);
        barraDeNavegacion.setOnNavigationItemSelectedListener(navListener);
        View view = barraDeNavegacion.findViewById(R.id.eventos);
        view.performClick();

        fabMenu = findViewById(R.id.fab_crear);
        fabMenu.setClosedOnTouchOutside(true);
        fabTareas = findViewById(R.id.fab_tareas);
        fabTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Crear_tarea_activity.class);
                startActivity(intent);
            }
        });
        fabEventos = findViewById(R.id.fab_eventos);
        fabEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        creadorDeTareasPrioridad = getIntent().getIntExtra("prioridadT", 0);


        if (creadorDeTareasPrioridad > 0) {
            creadorDeTareasNombre = getIntent().getStringExtra("nombreT");
            tarea = new Tarea(creadorDeTareasNombre);
            agenda.agregar(tarea);
        }

//        setFechaDeHoy();
        agenda.agregar(new Evento(fecha, "Evento"));
        agenda.agregar(new Evento(fecha, "Evento asdasd"));
        agenda.agregar(new Evento(fecha, "evento asdasdasd "));
        agenda.agregar(new Evento(fecha, "eventoasdasd"));
        agenda.agregar(new Evento(fecha, "eventoasdasd"));
        agenda.agregar(new Evento(fecha, "Evento"));
        agenda.agregar(new Evento(fecha, "Evento asdasd"));
        agenda.agregar(new Evento(fecha, "evento asdasdasd "));
        agenda.agregar(new Evento(fecha, "eventoasdasd"));
        agenda.agregar(new Evento(fecha, "eventoasdasd"));
        agenda.agregar(new Evento(fecha, "Evento"));
        agenda.agregar(new Evento(fecha, "Evento asdasd"));
        agenda.agregar(new Evento(fecha, "evento asdasdasd "));
        agenda.agregar(new Evento(fecha, "eventoasdasd"));
        agenda.agregar(new Evento(fecha, "eventoasdasd"));
        agenda.agregar(new Evento(fecha, "Evento"));
        agenda.agregar(new Evento(fecha, "Evento asdasd"));
        agenda.agregar(new Evento(fecha, "evento asdasdasd"));
        agenda.agregar(new Evento(fecha, "eventoasdasd"));
        agenda.agregar(new Evento(fecha, "eventoasdasd"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment SelectedFragment = null;

            switch (item.getItemId()){
                case R.id.backlog: {
                    SelectedFragment = new Fragment_backlog();
                    bundle.putParcelableArrayList("backlog", backlog);
                    SelectedFragment.setArguments(bundle);
                    break;
                }
                case R.id.historial: SelectedFragment = new Fragment_historial();break;
                case R.id.eventos: {
                    SelectedFragment = new Fragment_eventos();
                    bundle.putParcelableArrayList("eventos", eventos);
                    SelectedFragment.setArguments(bundle);
                    break;
                }
                case R.id.principal: SelectedFragment = new Fragment_shuffle();break;
                case R.id.calendario: SelectedFragment = new Fragment_calendario();break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragmentos,
                    SelectedFragment).commit();
            return true;
        }
    };



//    private void setFechaDeHoy() {
//        int dia = this.fecha.get(Calendar.DAY_OF_MONTH);
//        Locale locale = Locale.getDefault();
//        String mes = this.fecha.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
//        String diaDeLaSemana = this.fecha.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
//
//        TextView displayDia = findViewById(R.id.dia);
//        displayDia.setText(Integer.toString(dia));
//
//        TextView displayMes = findViewById(R.id.mes);
//        displayMes.setText(mes);
//
//        TextView displayDiaDeLaSemana = findViewById(R.id.dia_de_la_semana);
//        displayDiaDeLaSemana.setText(diaDeLaSemana);
//    }
}
