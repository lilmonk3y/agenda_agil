package com.devs.activity;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.devs.core.agenda.R;

public class CrearTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea_activity);

        final TextInputEditText nombreDeLaTareaInput = findViewById(R.id.nombre_de_la_tarea_input);
        final RadioGroup prioridad = findViewById(R.id.radio_group_prioridad);

        MaterialButton crearTarea = findViewById(R.id.crearTarea);
        crearTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreDeLaTarea = nombreDeLaTareaInput.getText().toString();

                if (nombreDeLaTarea.matches("") || nombreDeLaTarea.matches(" ") || nombreDeLaTarea.matches("  ")) {
                    Toast.makeText(CrearTareaActivity.this, "¡Debés ingresar un nombre de tarea!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    intent.putExtra("nombreT", nombreDeLaTarea);
                    intent.putExtra("prioridadT", 1);
                    Toast.makeText(CrearTareaActivity.this, "Tarea creada con éxito. ;)", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }



            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
