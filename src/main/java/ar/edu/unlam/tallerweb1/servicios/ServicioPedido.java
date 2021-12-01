package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosConfirmacion;
import ar.edu.unlam.tallerweb1.Excepciones.DireccionInexistente;
import ar.edu.unlam.tallerweb1.modelo.*;

import java.util.List;

public interface ServicioPedido {


    Pedido agregarProductoAlPedido(Long idProducto, Long idPedido);

    Pedido eliminarComidaDeUnPedido(Long idProducto, Long idPedido);

    Pedido obtenerPedido(Long idPedido);

    Long generarPedido(Routes routes);

    List<ItemPedido> obtenerItemsPedido(Long idPedido);

    Routes consultarRango(String calle, String altura, String localidad) throws DireccionInexistente;

    Calles listarCalles();

    void confirmarCompra(DatosConfirmacion datosConfirmacion);

    List<Pedido> listarPedidos();

    List<Pedido> listarPedidosPorEstado(String filtro);

    void cambiarEstadoDeUnPedido(Long idPedido, String estado);
}
