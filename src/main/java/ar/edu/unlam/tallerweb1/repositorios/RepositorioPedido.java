package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.ItemPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import ar.edu.unlam.tallerweb1.modelo.Viaje;

import java.util.List;

public interface RepositorioPedido {
    Pedido obtenerPedido(Long id);

    void actualizarPedido(Pedido pedido);

    Long generarPedido(Pedido pedido);

    ItemPedido obtenerItemPedido(Long idPedido, Long idProducto);

    void guardarItemPedido(ItemPedido itemPedido);

    List<ItemPedido> obtenerItemsPedido(Long idPedido);

    void eliminarItemPedido(ItemPedido itemPedido);

    Ubicacion obtenerLatitudLongitud(String calle, String altura);

    Viaje consultarDistanciaDelViaje(Ubicacion ubicacion);
}
