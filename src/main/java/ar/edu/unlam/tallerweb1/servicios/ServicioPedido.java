package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Plato;
import ar.edu.unlam.tallerweb1.modelo.Producto;

public interface ServicioPedido {


    Pedido agregarPlatoAlPedido(Long idProducto, Long idPedido);

    Pedido eliminarPlatoDeUnPedido(Long idProducto, Long idPedido);
}
