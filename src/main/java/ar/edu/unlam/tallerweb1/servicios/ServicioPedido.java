package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.ItemPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;

import java.util.List;

public interface ServicioPedido {


    Pedido agregarProductoAlPedido(Long idProducto, Long idPedido);

    Pedido eliminarComidaDeUnPedido(Long idProducto, Long idPedido);

    Pedido obtenerPedido(Long idPedido);

    Long generarPedido();

    List<ItemPedido> obtenerItemsPedido(Long idPedido);

    void consultarRango(String calle, String altura);

}
