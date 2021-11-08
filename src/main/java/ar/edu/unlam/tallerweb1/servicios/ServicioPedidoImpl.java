package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.PedidoInexistente;
import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.ItemPedido;
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
    public Pedido agregarProductoAlPedido(Long idProducto, Long idPedido) {

        Pedido pedido = repositorioPedido.obtenerPedido(idPedido);

        Producto producto = repositorioProducto.buscarProductoPorId(idProducto);

        ItemPedido itemPedido = repositorioPedido.obtenerItemPedido(idPedido,idProducto);

        if(itemPedido==null){
            itemPedido = new ItemPedido();
        }

        Integer cantidad = itemPedido.getCantidad();
        itemPedido.setCantidad(++cantidad);

        if(producto instanceof Comida){
            Double totalTiempo = calcularTotal("+",pedido.getTiempoPreparacion(),((Comida) producto).getTiempoDeCoccion());
            pedido.setTiempoPreparacion(totalTiempo);
        }

        Double totalPrecio = calcularTotal("+",pedido.getTotal(),producto.getPrecio());
        pedido.setTotal(totalPrecio);

        repositorioPedido.actualizarPedido(pedido);
        repositorioPedido.guardarItemPedido(itemPedido);



        return pedido;
    }

    @Override
    public Pedido eliminarComidaDeUnPedido(Long idProducto, Long idPedido) {

        Pedido pedido = repositorioPedido.obtenerPedido(idPedido);
        Producto producto = repositorioProducto.buscarProductoPorId(idProducto);

        ItemPedido itemPedido = repositorioPedido.obtenerItemPedido(idPedido,idProducto);
        Integer cantidad = itemPedido.getCantidad();
        itemPedido.setCantidad(--cantidad);

        if(producto instanceof Comida){
            Double totalTiempo = calcularTotal("-",pedido.getTiempoPreparacion(),((Comida) producto).getTiempoDeCoccion());
            pedido.setTiempoPreparacion(totalTiempo);
        }

        Double totalPrecio = calcularTotal("-",pedido.getTotal(),producto.getPrecio());
        pedido.setTotal(totalPrecio);

        repositorioPedido.actualizarPedido(pedido);
        repositorioPedido.guardarItemPedido(itemPedido);
        return pedido;
    }

    @Override
    public Pedido obtenerPedido(Long idPedido) {
        return null;
    }

    @Override
    public Long generarPedido() {
        Pedido pedido = new Pedido();

       Long idPedido = repositorioPedido.generarPedido(pedido);


        return idPedido;
    }

        private Double calcularTotal(String operacion, Double montoTotal, Double cantidadActualizar) {

        Double total = montoTotal;
        if (operacion.equals("+")) {
            total = total + cantidadActualizar;
        } else {
            total = total - cantidadActualizar;
        }
        return total;

    }



}
