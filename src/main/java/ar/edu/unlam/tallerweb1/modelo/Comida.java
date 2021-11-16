package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Comida")
public class Comida extends Producto{

    @Column(nullable = true)
    private Double tiempoDeCoccion;

    public Comida() {
    }

    public Comida(String nombre, double precio, Categoria categoria, Integer cantidadMeGusta, String nombreImagen, String descripcion, Double tiempoDeCoccion) {
        super(nombre, precio, categoria, cantidadMeGusta, nombreImagen, descripcion);
        this.tiempoDeCoccion = tiempoDeCoccion;
    }

    public Double getTiempoDeCoccion() {
        return tiempoDeCoccion;
    }

    public void setTiempoDeCoccion(Double tiempoDeCoccion) {
        this.tiempoDeCoccion = tiempoDeCoccion;
    }

}
