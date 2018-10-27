package com.devs.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Objects;

public class Evento implements Parcelable {
    private String titulo;
    private Calendar fecha;
    private Boolean realizado;

    public Evento(){
        this.realizado = false;
    }

    public Evento(Calendar fecha) {
        this.fecha = fecha;
        this.realizado = false;
    }

    public Evento(Calendar date, Tarea tarea) {
        this.fecha = date;
        this.realizado = false;
    }

    public Evento(Calendar fechaDeHoy, String titulo) {
        this.fecha = fechaDeHoy;
        this.titulo = titulo;
        this.realizado = false;
    }

    public Evento(Evento otro) {
        this.realizado = otro.realizado();
        this.titulo = otro.titulo();
        this.fecha = otro.fecha();
    }

    public Evento(String titulo) {
        this.titulo = titulo;
        this.realizado = false;
        this.fecha = null;
    }

    protected Evento(Parcel in) {
        titulo = in.readString();
        byte tmpRealizado = in.readByte();
        realizado = tmpRealizado == 0 ? null : tmpRealizado == 1;
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };

    public String titulo() {
        return this.titulo;
    }

    public Calendar fecha() {
        return this.fecha;
    }

    public boolean realizado() {
        return this.realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(titulo, evento.titulo) &&
                Objects.equals(fecha, evento.fecha) &&
                Objects.equals(realizado, evento.realizado);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeByte((byte) (realizado == null ? 0 : realizado ? 1 : 2));
    }
}
