package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosConfirmacion;
import ar.edu.unlam.tallerweb1.Excepciones.*;
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
            throw new PedidoInexistente("Este pedido no existe");
        }


        return repositorioPedido.obtenerPedido(idPedido);
    }

    @Override
    public Long generarPedido(Routes routes) {
        Pedido pedido = new Pedido();
        pedido.setCalle(routes.getCalle());
        pedido.setAltura(routes.getAltura());
        pedido.setLocalidad(routes.getLocalidad());
        pedido.setEstadoPedido(EstadoPedido.PREPARANDO);
        Long idPedido = repositorioPedido.generarPedido(pedido);


        return idPedido;
    }

    @Override
    public List<ItemPedido> obtenerItemsPedido(Long idPedido) {

        List<ItemPedido> itemPedidos = repositorioPedido.obtenerItemsPedido(idPedido);
        if(itemPedidos.size()<1){
            throw new PedidoVacio("Su pedido se encuentra vacío! Añada todos sus productos aquí.");
        }

        return itemPedidos;
    }

    @Override
    public Routes consultarRango(String calle, String altura, String localidad) throws DireccionInexistente {

       Ubicacion ubicacion = repositorioPedido.obtenerLatitudLongitud(calle,altura,localidad);
       if(ubicacion==null){
           throw new DireccionInexistente("No existe la direccion");
       }
       Routes viaje =  repositorioPedido.consultarDistanciaDelViaje(ubicacion);

       if(viaje.getDistance()>4000.0){
           throw new RangoInvalido("Lamentablemente no se encuentra dentro del rango de envios.");
       }

       Localidades localidades = repositorioPedido.obtenerLocalidad(localidad);
       viaje.setLocalidad(localidades.getLocalidades().get(0).getNombre());
       viaje.setAltura(altura);
       viaje.setCalle(calle);
       return viaje;
    }

    @Override
    public Calles listarCalles() {
        Calles calles = this.repositorioPedido.listarCalles();

        return calles;
    }

    @Override
    public void confirmarCompra(DatosConfirmacion datosConfirmacion) {
        Pedido pedido = obtenerPedido(datosConfirmacion.getIdPedido());

        pedido.setTiempoPreparacion(Double.parseDouble(datosConfirmacion.getTiempoPreparacion()));
        pedido.setTotal(Double.parseDouble(datosConfirmacion.getTotal()));
        pedido.setAltura(datosConfirmacion.getAltura());
        pedido.setCalle(datosConfirmacion.getCalle());
        pedido.setTelefono(datosConfirmacion.getTelefono());
        pedido.setNombre(datosConfirmacion.getNombre());
        pedido.setLocalidad(datosConfirmacion.getLocalidad());
        pedido.setEstadoPedido(EstadoPedido.VIAJANDO);

        repositorioPedido.actualizarPedido(pedido);

    }

    @Override
    public List<Pedido> listarPedidos() {
        List<Pedido> listaPedido = repositorioPedido.listarPedidos();

        if(listaPedido.size()<1){
            throw new listaPedidosNoEncontrada();
        }

        return listaPedido;
    }

    @Override
    public List<Pedido> listarPedidosPorEstado(String filtro) {

        EstadoPedido estado = validarEstadoPedido(filtro);
        if(estado==null){
            return listarPedidos();
        }

        List<Pedido> listaPedido = repositorioPedido.listarPedidoPorEstado(estado);

        if(listaPedido.size()<1){
            throw new listaPedidosNoEncontrada();
        }
        return listaPedido;
    }

    @Override
    public void cambiarEstadoDeUnPedido(Long idPedido, String cambio) {
        Pedido pedido = repositorioPedido.obtenerPedido(idPedido);

        if(pedido.getEstadoPedido()==EstadoPedido.FINALIZADO){
            throw new PedidoFinalizado();
        }

        EstadoPedido estado = validarEstadoPedido(cambio);

        pedido.setEstadoPedido(estado);
        repositorioPedido.actualizarPedido(pedido);
    }

    private EstadoPedido validarEstadoPedido(String estado){
        switch (estado){
            case "PREPARANDO":
                return EstadoPedido.PREPARANDO;
            case "VIAJANDO":
                return EstadoPedido.VIAJANDO;
            case "FINALIZADO":
                return EstadoPedido.FINALIZADO;
            default:
                return null;
        }
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
