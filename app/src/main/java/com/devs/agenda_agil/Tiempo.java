package com.devs.agenda_agil;

public enum Tiempo {
    DIA (1),
    SEMANA (2),
    MES (3),
    AÑO (4);

    private final int periodo;

    Tiempo(int periodo) {
        this.periodo = periodo;
    }

    public int getPeriodo(){
        return this.periodo;
    }

    public enum Intervalo {
        UNO (1),
        DOS (2),
        TRES (3),
        CUATRO (4),
        CINCO (5);

        private final int intervalo;

        Intervalo(int intervalo){
            this.intervalo = intervalo;
        }

        public int getIntervalo(){
            return this.intervalo;
        }
    }
}
