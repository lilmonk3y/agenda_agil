package com.devs.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devs.core.agenda.R;
import com.devs.core.entity.Evento;

import java.util.ArrayList;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolderEventos>{
    ArrayList<Evento> eventos = new ArrayList<Evento>();


    public EventosAdapter(ArrayList<Evento> eventos){
        this.eventos = eventos;
    }

    public class ViewHolderEventos extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView fecha;
        TextView eventoLetraCirculo;

        public ViewHolderEventos(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.evento_titulo);
            fecha = itemView.findViewById(R.id.evento_fecha);
            eventoLetraCirculo = itemView.findViewById(R.id.evento_letra_circulo);
        }
    }

    public EventosAdapter(Object p0) {
    }

    @NonNull
    @Override
    public EventosAdapter.ViewHolderEventos onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_eventos, null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new ViewHolderEventos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventosAdapter.ViewHolderEventos viewHolderTareas, int position) {
        String eventoTitulo = eventos.get(position).titulo();
        String eventoFecha = "10 - Octubre - 2018";
        String eventoLetraCirculo = "E";

        viewHolderTareas.titulo.setText(eventoTitulo);
        viewHolderTareas.fecha.setText(eventoFecha);
        viewHolderTareas.eventoLetraCirculo.setText(eventoLetraCirculo);
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }
}
