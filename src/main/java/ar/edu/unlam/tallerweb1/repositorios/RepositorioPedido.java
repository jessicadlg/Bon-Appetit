package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;

import java.util.ArrayList;

public interface RepositorioPedido {

    ArrayList<Pedido> getPedidos();
    Pedido crearPedido();
    Pedido agregarProducto(Long idPedido, Integer idProducto);
}
