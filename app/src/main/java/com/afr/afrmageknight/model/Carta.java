package com.afr.afrmageknight.model;

public class Carta {

    //Atributos
    private int numero;
    private String nombre;

    //Constructores
    public Carta() {

    }

    public Carta(int numero, String nombre) {
        super();
        this.numero = numero;
        this.nombre = nombre;
    }

    //Getters & Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Métodos
    @Override
    public String toString() {
        return "Carta [numero=" + numero + ", nombre=" + nombre + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + numero;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Carta other = (Carta) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (numero != other.numero)
            return false;
        return true;
    }

}
