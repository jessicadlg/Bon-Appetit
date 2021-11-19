package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

public class Calles {

    private List<Calle> calles;

    private Localidades localidad;

    public Localidades getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidades localidad) {
        this.localidad = localidad;
    }

    public List<Calle> getCalles() {
        return calles;
    }

    public void setCalles(List<Calle> calles) {
        this.calles = calles;
    }
}
