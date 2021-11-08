package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;

public interface ServicioPedido {


    Pedido agregarProductoAlPedido(Long idProducto, Long idPedido);

    Pedido eliminarComidaDeUnPedido(Long idProducto, Long idPedido);

    Pedido obtenerPedido(Long idPedido);

    Long generarPedido();
}
