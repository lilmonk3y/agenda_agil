package com.devs.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Tarea implements Parcelable, Comparable<Tarea>{
    private Prioridad prioridad;
    private String nombre;

    public Tarea(String nombre) {
        this.nombre = nombre;
        prioridad = Prioridad.SIN_PRIORIZAR;
    }

    public Tarea() {
        prioridad = Prioridad.SIN_PRIORIZAR;
    }

    public Tarea(String nombre_de_la_tarea, Prioridad prioridad) {
        this.nombre = nombre_de_la_tarea;
        this.prioridad = prioridad;
    }

    protected Tarea(Parcel in) {
        nombre = in.readString();
    }

    public static final Creator<Tarea> CREATOR = new Creator<Tarea>() {
        @Override
        public Tarea createFromParcel(Parcel in) {
            return new Tarea(in);
        }

        @Override
        public Tarea[] newArray(int size) {
            return new Tarea[size];
        }
    };

    public String nombre() {
        return this.nombre;
    }

    public int prioridad(){
        return this.prioridad.getNivel();
    }

    @Override
    public int compareTo(@NonNull Tarea otra) {
        if( this.prioridad() <= otra.prioridad()){
            return -1;
        }else{
            return 1;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
    }
}
