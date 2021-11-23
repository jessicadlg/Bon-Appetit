package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosConfirmacion;
import ar.edu.unlam.tallerweb1.Excepciones.DireccionInexistente;
import ar.edu.unlam.tallerweb1.modelo.Calles;
import ar.edu.unlam.tallerweb1.modelo.ItemPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Routes;

import java.util.List;

public interface ServicioPedido {


    Pedido agregarProductoAlPedido(Long idProducto, Long idPedido);

    Pedido eliminarComidaDeUnPedido(Long idProducto, Long idPedido);

    Pedido obtenerPedido(Long idPedido);

    Long generarPedido();

    List<ItemPedido> obtenerItemsPedido(Long idPedido);

    Routes consultarRango(String calle, String altura, String localidad) throws DireccionInexistente;

    Calles listarCalles();

    void confirmarCompra(DatosConfirmacion datosConfirmacion);
}
