package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.ItemPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;

public interface RepositorioPedido {
    Pedido obtenerPedido(Long id);

    void actualizarPedido(Pedido pedido);

    Long generarPedido(Pedido pedido);

    ItemPedido obtenerItemPedido(Long idPedido, Long idProducto);

    void guardarItemPedido(ItemPedido itemPedido);
}
