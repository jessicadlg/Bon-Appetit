package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;

import java.util.ArrayList;

public interface ServicioPedido {
    ArrayList<Pedido> getPedidos();

    Pedido crearPedido();

    Pedido agregarProducto(Integer idPedido, Integer idProducto);
}
