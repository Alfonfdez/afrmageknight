package com.afr.afrmageknight.model;

public class Ronda {

    //Atributos
    private int numero;
    private TipoTactica tipoRonda;

    //Constructores
    public Ronda() {
    }

    public Ronda(int numero, TipoTactica tipoRonda) {
        this.numero = numero;
        this.tipoRonda = tipoRonda;
    }

    ////Getters & Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoTactica getTipoRonda() {
        return tipoRonda;
    }

    public void setTipoRonda(TipoTactica tipoRonda) {
        this.tipoRonda = tipoRonda;
    }
}
