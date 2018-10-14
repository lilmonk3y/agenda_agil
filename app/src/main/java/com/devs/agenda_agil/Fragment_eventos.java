package com.devs.agenda_agil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Fragment_eventos extends Fragment {
    private ArrayList<Evento> eventos;
    private RecyclerView recyclerViewEventos;
    private RecyclerView.Adapter eventosAdapter;
    private RecyclerView.LayoutManager eventosLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_eventos, container, false);

        eventos = this.getArguments().getParcelableArrayList("eventos");

        recyclerViewEventos = v.findViewById(R.id.recycler_view_eventos);
        recyclerViewEventos.setHasFixedSize(true);
        eventosLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewEventos.setLayoutManager(eventosLayoutManager);
        eventosAdapter = new EventosAdapter(eventos);
        recyclerViewEventos.setAdapter(eventosAdapter);

        return v;
    }
}
