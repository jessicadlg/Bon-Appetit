package ar.edu.unlam.tallerweb1.modelo;

public class Direccion {

    private Ubicacion ubicacion;

    public Direccion(){
        this.ubicacion = new Ubicacion();
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
