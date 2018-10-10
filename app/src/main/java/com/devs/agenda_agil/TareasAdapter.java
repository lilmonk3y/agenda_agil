package com.devs.agenda_agil;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.ViewHolderTareas>{
    ArrayList<Tarea> tareas = new ArrayList<Tarea>();


    public TareasAdapter(ArrayList<Tarea> tareas){
        this.tareas = tareas;
    }

    public class ViewHolderTareas extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView prioridad;
        TextView tareaLetraCirculo;

        public ViewHolderTareas(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tarea_titulo);
            prioridad = itemView.findViewById(R.id.tarea_prioridad);
            tareaLetraCirculo = itemView.findViewById(R.id.tarea_letra_circulo);
        }
    }

    public TareasAdapter(Object p0) {
    }

    @NonNull
    @Override
    public TareasAdapter.ViewHolderTareas onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_tareas, null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new ViewHolderTareas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareasAdapter.ViewHolderTareas viewHolderTareas, int position) {
        int tareaPrioridad = tareas.get(position).prioridad();
        String tareaPrioridadText;
        String tareaLetraCirculo;

        switch (tareaPrioridad){
            case 1: {
                tareaPrioridadText = "Prioridad: Alta.";
                tareaLetraCirculo = "1";
            }break;
            case 2: {
                tareaPrioridadText = "Prioridad: Media.";
                tareaLetraCirculo = "2";
            }break;
            case 3: {
                tareaPrioridadText = "Prioridad: Baja.";
                tareaLetraCirculo = "3";
            }break;
            case 4: {
                tareaPrioridadText = "Prioridad: Sin prioridad.";
                tareaLetraCirculo = "4";
            }break;
            default: {
                tareaPrioridadText = "def";
                tareaLetraCirculo = "T";
            }break;
        }
        viewHolderTareas.titulo.setText(tareas.get(position).nombre());
        viewHolderTareas.prioridad.setText(tareaPrioridadText);
        viewHolderTareas.tareaLetraCirculo.setText(tareaLetraCirculo);
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }


}
