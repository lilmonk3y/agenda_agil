package com.devs.agenda_agil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

class EventoCiclico {

    private final String titulo;
    private final List<Calendar> fechas;

    public EventoCiclico(Calendar fecha_de_inicio_de_repeticiones, Calendar fecha_de_finalizacion_de_repeticiones, String titulo, Tiempo periodo_entre_repeticiones, Tiempo.Intervalo intervalo_del_periodo) {
        int cantidad_de_dias_a_evaluar = duracion_de_repeticiones_en_dias(fecha_de_inicio_de_repeticiones, fecha_de_finalizacion_de_repeticiones);
        this.titulo = titulo;
        this.fechas = obtener_fechas_de_repeticion(fecha_de_inicio_de_repeticiones , cantidad_de_dias_a_evaluar, periodo_entre_repeticiones, intervalo_del_periodo);
    }

    public EventoCiclico(Calendar fecha_de_inicio_de_repeticiones, Calendar fecha_de_finalizacion_de_repeticiones, String titulo, int[] dias_de_repeticion) {
        int cantidad_de_dias_a_evaluar = duracion_de_repeticiones_en_dias(fecha_de_inicio_de_repeticiones, fecha_de_finalizacion_de_repeticiones);

        this.titulo = titulo;
        this.fechas = obtener_fechas_de_repeticion(fecha_de_inicio_de_repeticiones, cantidad_de_dias_a_evaluar, dias_de_repeticion);
    }

    private List<Calendar> obtener_fechas_de_repeticion(Calendar fecha_de_inicio_de_repeticiones, int cantidad_de_dias_a_evaluar, int[] dias_de_repeticion) {
        List<Calendar> fechas_obtenidas = new ArrayList<>();

        for (int dias = 0 ; dias < cantidad_de_dias_a_evaluar; dias++){
            Calendar fecha_a_evaluar = new GregorianCalendar(fecha_de_inicio_de_repeticiones.get(Calendar.YEAR),
                    fecha_de_inicio_de_repeticiones.get(Calendar.MONTH), fecha_de_inicio_de_repeticiones.get(Calendar.DAY_OF_MONTH));
            fecha_a_evaluar.add(Calendar.DAY_OF_YEAR, dias);

            for (int i : dias_de_repeticion){
                if (fecha_a_evaluar.get(Calendar.DAY_OF_WEEK) == i){
                    fechas_obtenidas.add(fecha_a_evaluar);
                    System.out.println(fecha_a_evaluar.getTime());
                }
            }
        }

        return null;
    }

    private List<Calendar> obtener_fechas_de_repeticion(Calendar fecha_de_inicio_de_repeticiones, int cantidad_de_dias_a_evaluar, Tiempo periodo_entre_repeticiones, Tiempo.Intervalo intervalo_del_periodo) {
        List<Calendar> fechas_obtenidas = new ArrayList<>();
        List<Calendar> fechas_sin_intervalo = new ArrayList<>();

        int año_de_inicio_de_repeticiones = fecha_de_inicio_de_repeticiones.get(Calendar.YEAR);
        int mes_de_inicio_de_repeticiones = fecha_de_inicio_de_repeticiones.get(Calendar.MONTH);
        int dia_del_mes_de_inicio_de_repeticiones = fecha_de_inicio_de_repeticiones.get(Calendar.DAY_OF_MONTH);
        int dia_de_la_semana_de_inicio_de_repeticiones = fecha_de_inicio_de_repeticiones.get(Calendar.DAY_OF_WEEK);
        int dia_del_año_de_inicio_de_repeticiones = fecha_de_inicio_de_repeticiones.get(Calendar.DAY_OF_YEAR);

        for (int dias = 0; dias <= cantidad_de_dias_a_evaluar; dias++) {
            Calendar fechas_a_evaluar= new GregorianCalendar(año_de_inicio_de_repeticiones,
                mes_de_inicio_de_repeticiones,
                dia_del_mes_de_inicio_de_repeticiones);

            switch (periodo_entre_repeticiones){
                case DIA: {fechas_a_evaluar.add(Calendar.DAY_OF_YEAR, dias);
                    fechas_sin_intervalo.add(fechas_a_evaluar);
                    break;
                }
                case SEMANA: {fechas_a_evaluar.add(Calendar.DAY_OF_YEAR, dias);
                    if (dia_de_la_semana_de_inicio_de_repeticiones == fechas_a_evaluar.get(Calendar.DAY_OF_WEEK)){
                        fechas_sin_intervalo.add(fechas_a_evaluar);
                    }
                    break;
                }
                case MES: {fechas_a_evaluar.add(Calendar.DAY_OF_YEAR, dias);
                    if (dia_del_mes_de_inicio_de_repeticiones == fechas_a_evaluar.get(Calendar.DAY_OF_MONTH)){
                        fechas_sin_intervalo.add(fechas_a_evaluar);
                    }
                    break;
                }
                case AÑO: {fechas_a_evaluar.add(Calendar.DAY_OF_YEAR, dias);
                    if (dia_del_año_de_inicio_de_repeticiones == fechas_a_evaluar.get(Calendar.DAY_OF_YEAR)){
                        fechas_sin_intervalo.add(fechas_a_evaluar);
                    }
                    break;
                }
            }
        }



        for (int i = 0; i < fechas_sin_intervalo.size(); i+=intervalo_del_periodo.getIntervalo()) {
            fechas_obtenidas.add(fechas_sin_intervalo.get(i));
        }

        return fechas_obtenidas;
    }

    private int duracion_de_repeticiones_en_dias(Calendar fecha_de_inicio_de_repeticiones, Calendar fecha_de_finalizacion_de_repeticiones) {
        int año_de_inicio_de_repeticiones = fecha_de_inicio_de_repeticiones.get(Calendar.YEAR);
        int año_de_finalizacion_de_repeticiones = fecha_de_finalizacion_de_repeticiones.get(Calendar.YEAR);
        int dia_del_año_de_inicio_de_repeticiones = fecha_de_inicio_de_repeticiones.get(Calendar.DAY_OF_YEAR);
        int dia_del_año_de_finalizacion_de_repeticiones = fecha_de_finalizacion_de_repeticiones.get(Calendar.DAY_OF_YEAR);
        int dias_en_el_año = 365;
        int dif_de_años = año_de_finalizacion_de_repeticiones - año_de_inicio_de_repeticiones;

        assert (dif_de_años >= 0);
        if (dif_de_años == 0) {
            assert (dia_del_año_de_inicio_de_repeticiones < dia_del_año_de_finalizacion_de_repeticiones);
        }

        int duracion_de_las_repeticiones_en_dias = dias_en_el_año * dif_de_años - dia_del_año_de_inicio_de_repeticiones + dia_del_año_de_finalizacion_de_repeticiones;
        return duracion_de_las_repeticiones_en_dias;
    }

    public List<Calendar> getFechas() {
        return fechas;
    }
}
