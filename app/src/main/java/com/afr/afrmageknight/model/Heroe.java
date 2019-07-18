package com.afr.afrmageknight.model;

import java.util.ArrayList;

public class Heroe {

    //Atributos
    private String nombre;
    private ArrayList<Cristal> cristales;
    private ArrayList<CartaAvanzada> cartasAccionBasica;
    private ArrayList<FichaHabilidad> fichasHabilidad;

    //Constructores
    public Heroe() {
        // TODO Auto-generated constructor stub
    }

    public Heroe(String nombre, ArrayList<Cristal> cristales, ArrayList<CartaAvanzada> cartasAccionBasica,
                 ArrayList<FichaHabilidad> fichasHabilidad) {
        super();
        this.nombre = nombre;
        this.cristales = cristales;
        this.cartasAccionBasica = cartasAccionBasica;
        this.fichasHabilidad = fichasHabilidad;
    }

    //Getters & Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Cristal> getCristales() {
        return cristales;
    }

    public void setCristales(ArrayList<Cristal> cristales) {
        this.cristales = cristales;
    }

    public ArrayList<CartaAvanzada> getCartasAccionBasica() {
        return cartasAccionBasica;
    }

    public void setCartasAccionBasica(ArrayList<CartaAvanzada> cartasAccionBasica) {
        this.cartasAccionBasica = cartasAccionBasica;
    }

    public ArrayList<FichaHabilidad> getFichasHabilidad() {
        return fichasHabilidad;
    }

    public void setFichasHabilidad(ArrayList<FichaHabilidad> fichasHabilidad) {
        this.fichasHabilidad = fichasHabilidad;
    }

    //Métodos
    @Override
    public String toString() {
        return "Heroe [nombre=" + nombre + ", cristales=" + cristales + ", cartasAccionBasica=" + cartasAccionBasica
                + ", fichasHabilidad=" + fichasHabilidad + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cartasAccionBasica == null) ? 0 : cartasAccionBasica.hashCode());
        result = prime * result + ((cristales == null) ? 0 : cristales.hashCode());
        result = prime * result + ((fichasHabilidad == null) ? 0 : fichasHabilidad.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        Heroe other = (Heroe) obj;
        if (cartasAccionBasica == null) {
            if (other.cartasAccionBasica != null)
                return false;
        } else if (!cartasAccionBasica.equals(other.cartasAccionBasica))
            return false;
        if (cristales == null) {
            if (other.cristales != null)
                return false;
        } else if (!cristales.equals(other.cristales))
            return false;
        if (fichasHabilidad == null) {
            if (other.fichasHabilidad != null)
                return false;
        } else if (!fichasHabilidad.equals(other.fichasHabilidad))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

}
