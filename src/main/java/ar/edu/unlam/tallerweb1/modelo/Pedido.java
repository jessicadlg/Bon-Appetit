package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;

public class Pedido {

    private String estado;
    private Integer numero;
    private ArrayList<Producto> productosPedidos;

    public Pedido() {
        this.productosPedidos = new ArrayList<Producto>();
    }

    public Pedido(Integer numero) {
        this.numero = numero;
        this.estado = "En Curso";
        this.productosPedidos = new ArrayList<Producto>();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setProductosPedidos(ArrayList<Producto> productosPedidos) {
        this.productosPedidos = productosPedidos;
    }

    public ArrayList<Producto> getProductosPedidos() {
        return this.productosPedidos;
    }
}
