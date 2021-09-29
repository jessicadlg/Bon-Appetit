package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ListaPedidosException;
import ar.edu.unlam.tallerweb1.Excepciones.PedidoNoCreadoException;
import ar.edu.unlam.tallerweb1.modelo.Pedido;

import java.util.ArrayList;

public interface ServicioPedido {
    ArrayList<Pedido> getPedidos() throws ListaPedidosException;

    Pedido crearPedido() throws PedidoNoCreadoException;

    Pedido agregarProducto(Integer idPedido, Integer idProducto);
}
