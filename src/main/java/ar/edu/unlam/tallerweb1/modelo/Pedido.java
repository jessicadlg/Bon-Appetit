package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.ArrayList;

public class Pedido {

    private Long id;
    private String estado;
    private ArrayList<Producto> productosPedidos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido() {
        this.productosPedidos = new ArrayList<Producto>();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setProductosPedidos(ArrayList<Producto> productosPedidos) {
        this.productosPedidos = productosPedidos;
    }

    public ArrayList<Producto> getProductosPedidos() {
        return this.productosPedidos;
    }
}
