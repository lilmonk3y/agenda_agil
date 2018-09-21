package com.devs.agenda_agil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CrearEventoTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento_tarea);

        View CrearVolver = findViewById(R.id.crear_volver);
        CrearVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
