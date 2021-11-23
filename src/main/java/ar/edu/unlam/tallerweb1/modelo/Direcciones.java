package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.List;

public class Direcciones {

    private List<Direccion> direcciones;

    public Direcciones(){
        this.direcciones = new ArrayList<Direccion>();
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
}
