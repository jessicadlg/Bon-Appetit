package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;

public class Pedido {

    private ArrayList<Producto> productosPedidos;

    public Pedido() {
        this.productosPedidos = new ArrayList<Producto>();
    }

    public void agregarProducto(Producto producto) {
        this.productosPedidos.add(producto);
    }

    public ArrayList<Producto> getProductosPedidos() {
        return this.productosPedidos;
    }

    public ArrayList<Producto> eliminarProducto(String codigoProducto) {
        return null;
    }
}
