package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.PedidoInexistente;
import ar.edu.unlam.tallerweb1.Excepciones.PedidoVacio;
import ar.edu.unlam.tallerweb1.Excepciones.RangoInvalido;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioPedidoImpl implements ServicioPedido {


    private RepositorioPedido repositorioPedido;
    private RepositorioProducto repositorioProducto;
    private Ubicacion UBICACION_RESTAURANTE = new Ubicacion(-58.56302836691231,-34.67052234258952);

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
        actualizarItem(itemPedido,producto,pedido);
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
        if(cantidad==0){
            repositorioPedido.eliminarItemPedido(itemPedido);
        }else{
            repositorioPedido.guardarItemPedido(itemPedido);
        }

        if(producto instanceof Comida){
            Double totalTiempo = calcularTotal("-",pedido.getTiempoPreparacion(),((Comida) producto).getTiempoDeCoccion());
            pedido.setTiempoPreparacion(totalTiempo);
        }

        Double totalPrecio = calcularTotal("-",pedido.getTotal(),producto.getPrecio());
        pedido.setTotal(totalPrecio);

        repositorioPedido.actualizarPedido(pedido);
        return pedido;
    }

    @Override
    public Pedido obtenerPedido(Long idPedido) {

        if(repositorioPedido.obtenerPedido(idPedido)==null){
            throw new PedidoInexistente();
        }


        return repositorioPedido.obtenerPedido(idPedido);
    }

    @Override
    public Long generarPedido() {
        Pedido pedido = new Pedido();

       Long idPedido = repositorioPedido.generarPedido(pedido);


        return idPedido;
    }

    @Override
    public List<ItemPedido> obtenerItemsPedido(Long idPedido) {

        List<ItemPedido> itemPedidos = repositorioPedido.obtenerItemsPedido(idPedido);
        if(itemPedidos.size()<1){
            throw new PedidoVacio();
        }

        return itemPedidos;
    }

    @Override
    public void consultarRango(String calle, String altura, String localidad) {

       Ubicacion ubicacion = repositorioPedido.obtenerLatitudLongitud(calle,altura,localidad);
       Routes viaje =  repositorioPedido.consultarDistanciaDelViaje(ubicacion);

       if(viaje.getDistance()>4000.0){
           throw new RangoInvalido();
       }
    }

    @Override
    public Calles listarCalles() {
        Calles calles = this.repositorioPedido.listarCalles();

        return calles;
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

    private void actualizarItem(ItemPedido itemPedido,Producto producto,Pedido pedido){

        if(itemPedido.getProducto()==null){
            itemPedido.setProducto(producto);
        }
        if(itemPedido.getPedido()==null){
            itemPedido.setPedido(pedido);
        }

    }



}
