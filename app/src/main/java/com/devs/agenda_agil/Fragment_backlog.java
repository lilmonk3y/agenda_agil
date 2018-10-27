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

import com.devs.entity.Tarea;

import java.util.ArrayList;


public class Fragment_backlog extends Fragment {
    private ArrayList<Tarea> backlog;
    private RecyclerView recyclerViewTareas;
    private RecyclerView.Adapter tareasAdapter;
    private RecyclerView.LayoutManager tareasLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_backlog, container, false);

        backlog = this.getArguments().getParcelableArrayList("backlog");

        recyclerViewTareas = v.findViewById(R.id.recycler_view_tareas);
        recyclerViewTareas.setHasFixedSize(true);
        tareasLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewTareas.setLayoutManager(tareasLayoutManager);
        tareasAdapter = new TareasAdapter(backlog);
        recyclerViewTareas.setAdapter(tareasAdapter);

        return v;
    }
}
