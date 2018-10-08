package com.devs.agenda_agil;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TareasArrayAdapter extends ArrayAdapter<Tarea>{

    public TareasArrayAdapter(@NonNull AppCompatActivity context, ArrayList<Tarea> backlog) {

        super(context, 0, backlog);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_tareas, parent, false);
        }

        Tarea currentTarea = getItem(position);

        TextView tareaNombreText = (TextView) listItemView.findViewById(R.id.tarea_nombre);
        tareaNombreText.setText(currentTarea.nombre());

        TextView prioridadText = (TextView) listItemView.findViewById(R.id.tarea_prioridad);
        prioridadText.setText(Integer.toString(currentTarea.prioridad()));

//        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
//
//        iconView.setImageResource(currentTarea.getImageResourceId());

        return listItemView;
    }
}
