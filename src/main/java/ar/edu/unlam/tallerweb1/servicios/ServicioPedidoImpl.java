package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioPedidoImpl implements ServicioPedido {

    private RepositorioPedido repositorioPedido;
    private RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioPedidoImpl(RepositorioPedido repositorioPedido, RepositorioProducto repositorioProducto) {
        this.repositorioPedido = repositorioPedido;
        this.repositorioProducto = repositorioProducto;

    }

    @Override
    public Pedido agregarComidaAlPedido(Long idProducto, Long idPedido) {

        Pedido pedido = repositorioPedido.obtenerPedido(idPedido);
        if(pedido == null)
            pedido= new Pedido();
        Producto producto = repositorioProducto.buscarProductoPorId(idProducto);

        pedido.getListaProductos().add(producto);

        Double total = calcularTotal("+", pedido.getTotal(), producto.getPrecio());
        pedido.setTotal(total);

        repositorioPedido.actualizarPedido(pedido);

        return pedido;
    }

    @Override
    public Pedido eliminarComidaDeUnPedido(Long idProducto, Long idPedido) {

        Pedido pedido = repositorioPedido.obtenerPedido(idPedido);
        Producto producto = repositorioProducto.buscarProductoPorId(idProducto);

        if (pedido.getListaProductos().contains(producto)) {
            pedido.getListaProductos().remove(producto);
        }
        Double total = calcularTotal("-", pedido.getTotal(), producto.getPrecio());
        pedido.setTotal(total);

        repositorioPedido.actualizarPedido(pedido);
        return pedido;
    }


    private Double calcularTotal(String operacion,Double pedidoTotal,Double precioActualizar){

        Double total = pedidoTotal;
        if(operacion.equals("+")){
            total = total + precioActualizar;
        }else{
            total = total - precioActualizar;
        }
        return total;

    }


}
