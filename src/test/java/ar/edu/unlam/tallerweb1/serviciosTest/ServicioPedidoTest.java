package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosConfirmacion;
import ar.edu.unlam.tallerweb1.Excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedidoImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioPedidoTest {

    private RepositorioPedido repositorioPedido = mock(RepositorioPedido.class);
    private RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
    private ServicioPedido servicioPedido = new ServicioPedidoImpl(repositorioPedido, repositorioProducto);
    Pedido pedido;
    private Long idProducto = 2L;
    private Long idPedido = 1L;
    private final String CALLE = "Calle falsa";
    private final String ALTURA = "123";
    private String LOCALIDAD = "06427010014";
    private List<Pedido> listaPedidos;

    @Test
    public void quePuedaConsultarSiEstaDentroDelRangoDeEnvios() throws DireccionInexistente {

        givenUnaCalleYUnaAlturaDentroDelRango();

        whenConsultoElRango();

        thenPuedoAgregarLosProductos();

    }

    @Test(expected = RangoInvalido.class)
    public void quePuedaConsultarSiEstaFueraDelRangoDeEnvios() throws DireccionInexistente {

        givenUnaCalleYUnaAlturaFueraDelRango();

        whenConsultoElRango();

    }

    @Test
    public void queSePuedanListarLasCallesYLocalidades(){

        givenQueExisteLasCalles();

        Calles calles = whenListoLasCalles();

        thenMeDevuelveLasCallesYLocalidades(calles);

    }

    @Test(expected = DireccionInexistente.class)
    public void queSiBuscoUnaDireccionInexistenteLanzeUnDireccionInexistenteException() throws DireccionInexistente {

        givenQueBuscoUnaDireccionInexistente();

        whenConsultoElRango();


    }


    @Test
    public void queSePuedaGenerarUnPedido() {

        givenUnPedidoNuevo();

        Long idPedido = whenQuieroGenerarElPedido();

        thenObtengoElIdDelPedidoGenerado(idPedido);

    }

    @Test(expected = PedidoVacio.class)
    public void queCuandoElPedidoEsteVacioLanzeUnaExceptionDePedidoVacio(){

        givenUnPedidoNuevoSinProductos();

        whenSeMuestraElPedido();

    }

    @Test
    public void queSePuedaAgregarDosVecesUnaComidaAUnPedidoYQueSeCalculeElTotalYElTiempoDeCoccion() {
        givenQueExisteUnPedidoCon(20.0, 200.0);

        whenAgregoUnaComidaCon(40.0, 750.0);

        thenMeDevuelveUnPedidoCon(60.0, 950.0);
    }

    @Test
    public void queSePuedaAgregarUnaBebidaAUnPedidoYQueSeCalculeElTotal() {

        givenQueExisteUnPedidoSinBebidas();

        whenAgregoUnaBebida();

        thenMeDevuelveElPedido();

    }

    @Test
    public void queSePuedaEliminarUnaComidaDeUnPedido() {

        givenQueExisteUnPedidoConProductosDentro();

        whenQuieroEliminarUnProducto();

        thenMeEliminaElProducto();

    }

    @Test
    public void queSePuedaComprobarQueUnPedidoSeRealiza(){

       DatosConfirmacion datosConfirmacion = givenQueExisteUnPedidoCompleto();

       whenConfirmoLaCompraDelPedido(datosConfirmacion);

       thenElPedidoSeActualiza();

    }

    @Test
    public void queSePuedanListaTodosLosPedidos(){

        givenQueExisteUnaListaDePedidos();

        whenListoLosPedidos();

        thenMeDevuelveLaListaDePedidos();

    }

    @Test(expected = listaPedidosNoEncontrada.class)
    public void queSiNoExisteUnaListaDePedidosLanzeUnListaPedidosNoEncontradaException(){

        givenQueNoExisteUnaListaDePedidos();

        whenListoLosPedidos();

    }

    @Test
    public void queSePuedaListarUnPedidoPorUnEstadoEspecifico(){

        givenQueExisteUnaListaDePedidosConUnEstadoEspecifico();

        whenListoLosPedidosPorEseEstadoEspecifico();

        thenMeDevuelveLosPedidosConEseEstado();

    }

    @Test
    public void queSePuedaCambiarElEstadoDeUnPedido(){
        givenQueExisteUnPedidoConUnEstado();

        whenQuieroCambiarElEstadoDelPedido();

        thenElPedidoSeCambiaDeEstado();
    }

    @Test(expected = PedidoFinalizado.class)
    public void queNoSePuedaCambiarDeEstadoUnPedidoFinalizado(){

        givenQueExisteUnPedidoConUnEstadoFinalizado();

        whenQuieroCambiarElEstadoDelPedidoFinalizado();

    }

    private void givenQueExisteUnPedidoConUnEstadoFinalizado() {
        Pedido p1 = new Pedido();
        p1.setEstadoPedido(EstadoPedido.FINALIZADO);
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(p1);
    }

    private void whenQuieroCambiarElEstadoDelPedidoFinalizado() {
        servicioPedido.cambiarEstadoDeUnPedido(idPedido,"FINALIZADO");
    }

    private void givenQueExisteUnPedidoConUnEstado() {
        Pedido p1 = new Pedido();
        p1.setEstadoPedido(EstadoPedido.PREPARANDO);
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(p1);

    }

    private void whenQuieroCambiarElEstadoDelPedido() {
        servicioPedido.cambiarEstadoDeUnPedido(idPedido,"PREPARANDO");
    }

    private void thenElPedidoSeCambiaDeEstado() {
        verify(repositorioPedido,times(1)).actualizarPedido(any(Pedido.class));
    }

    private void givenQueExisteUnaListaDePedidosConUnEstadoEspecifico() {
        List<Pedido> listaPedidos = new ArrayList<>();
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        Pedido p3 = new Pedido();
        listaPedidos.add(p1);
        listaPedidos.add(p2);
        listaPedidos.add(p3);
        when(repositorioPedido.listarPedidoPorEstado(any())).thenReturn(listaPedidos);
    }

    private void whenListoLosPedidosPorEseEstadoEspecifico() {
        listaPedidos = servicioPedido.listarPedidosPorEstado("PREPARANDO");
    }

    private void thenMeDevuelveLosPedidosConEseEstado() {
        assertThat(listaPedidos).isNotNull();
        assertThat(listaPedidos).hasSize(3);
    }

    private void givenQueNoExisteUnaListaDePedidos() {
        when(repositorioPedido.listarPedidos()).thenReturn(new ArrayList<>());
    }

    private void givenQueExisteUnaListaDePedidos() {
        List<Pedido> listaPedidos = new ArrayList<>();
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        Pedido p3 = new Pedido();
        listaPedidos.add(p1);
        listaPedidos.add(p2);
        listaPedidos.add(p3);
        when(repositorioPedido.listarPedidos()).thenReturn(listaPedidos);
    }

    private void whenListoLosPedidos() {
        listaPedidos = servicioPedido.listarPedidos();
    }

    private void thenMeDevuelveLaListaDePedidos() {
        assertThat(listaPedidos).isNotNull();
        assertThat(listaPedidos).hasSize(3);
    }

    private DatosConfirmacion givenQueExisteUnPedidoCompleto() {
        DatosConfirmacion datosConfirmacion = new DatosConfirmacion();
        datosConfirmacion.setAltura("200");
        datosConfirmacion.setCalle("calle falsa");
        datosConfirmacion.setLocalidad("La Matanza");
        datosConfirmacion.setTelefono("3123131");
        datosConfirmacion.setTiempoPreparacion("20.0");
        datosConfirmacion.setNombre("Taller web");
        datosConfirmacion.setTotal("500.0");
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(new Pedido());
        return datosConfirmacion;
    }

    private void whenConfirmoLaCompraDelPedido(DatosConfirmacion datosConfirmacion) {
        servicioPedido.confirmarCompra(datosConfirmacion);
    }

    private void thenElPedidoSeActualiza() {
        verify(repositorioPedido,times(1)).actualizarPedido(any(Pedido.class));
    }


    private void givenQueExisteUnPedidoConProductosDentro() {
        Comida comida = new Comida();
        Bebida bebida = new Bebida();
        ItemPedido itemPedido = new ItemPedido();
        comida.setPrecio(200.0);
        comida.setTiempoDeCoccion(100.0);
        comida.setNombre("Pizza");
        bebida.setPrecio(300.0);
        bebida.setNombre("Agua");
        Pedido pedido = new Pedido();
        pedido.setTotal(500.0);
        pedido.setTiempoPreparacion(100.0);

        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(repositorioProducto.buscarProductoPorId(anyLong())).thenReturn(comida);
        when(repositorioPedido.obtenerItemPedido(anyLong(), anyLong())).thenReturn(itemPedido);
    }

    private void whenQuieroEliminarUnProducto() {
        pedido = servicioPedido.eliminarComidaDeUnPedido(idProducto, idPedido);
    }

    private void thenMeEliminaElProducto() {
        assertThat(pedido).isNotNull();
        assertThat(pedido.getTotal()).isEqualTo(300.0);
        assertThat(pedido.getTiempoPreparacion()).isEqualTo(0.0);
    }

    private void givenQueExisteUnPedidoSinBebidas() {
        Pedido pedido = new Pedido();
        Bebida producto = new Bebida();
        ItemPedido itemPedido = new ItemPedido();
        producto.setPrecio(50.0);
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(repositorioProducto.buscarProductoPorId(anyLong())).thenReturn(producto);
        when(repositorioPedido.obtenerItemPedido(anyLong(), anyLong())).thenReturn(itemPedido);

    }

    private void whenAgregoUnaBebida() {
        pedido = servicioPedido.agregarProductoAlPedido(idProducto, idPedido);
    }

    private void thenMeDevuelveElPedido() {
        assertThat(pedido).isNotNull();
        assertThat(pedido.getTotal()).isEqualTo(50.0);
        assertThat(pedido.getTiempoPreparacion()).isEqualTo(0.0);
    }

    private void givenQueExisteUnPedidoCon(double tiempoCoccion, double costo) {
        Pedido pedido = new Pedido();
        Comida producto = new Comida();
        producto.setPrecio(750.0);
        producto.setTiempoDeCoccion(40.0);
        ItemPedido itemPedido = new ItemPedido();
        pedido.setTiempoPreparacion(tiempoCoccion);
        pedido.setTotal(costo);
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(repositorioProducto.buscarProductoPorId(anyLong())).thenReturn(producto);
        when(repositorioPedido.obtenerItemPedido(anyLong(), anyLong())).thenReturn(itemPedido);
    }

    private void whenAgregoUnaComidaCon(double tiempoCoccion, double costo) {
        pedido = servicioPedido.agregarProductoAlPedido(idProducto, idPedido);
    }

    private void thenMeDevuelveUnPedidoCon(double tiempoCoccion, double costo) {
        assertThat(pedido.getTotal()).isEqualTo(costo);
        assertThat(pedido.getTiempoPreparacion()).isEqualTo(tiempoCoccion);
    }

    private void givenUnPedidoNuevo() {
        when(repositorioPedido.generarPedido(anyObject())).thenReturn(1L);
    }

    private void whenSeMuestraElPedido() {
        pedido = servicioPedido.obtenerPedido(idPedido);
    }

    private void givenUnPedidoNuevoSinProductos() {
        when(repositorioPedido.obtenerPedido(idPedido)).thenThrow(PedidoVacio.class);
    }

    private Long whenQuieroGenerarElPedido() {
        Routes routes = new Routes();
        routes.setCalle("Calle");
        routes.setLocalidad("La Matanza");
        routes.setAltura("200");
        routes.setDuration(10.0);
        routes.setDistance(100.0);
        return servicioPedido.generarPedido(routes);
    }

    private void thenObtengoElIdDelPedidoGenerado(Long idPedido) {
        assertThat(idPedido).isEqualTo(1L);
    }

    private void givenQueBuscoUnaDireccionInexistente() {
        when(repositorioPedido.obtenerLatitudLongitud(CALLE,ALTURA,LOCALIDAD)).thenReturn(null);
    }

    private void givenQueExisteLasCalles() {
        Calles calles = new Calles();
        Calle c1 = new Calle();
        Calle c2 = new Calle();
        Calle c3 = new Calle();
        List<Calle> callesLista = new ArrayList<>();
        callesLista.add(c1);
        callesLista.add(c2);
        callesLista.add(c3);
        calles.setCalles(callesLista);
        when(repositorioPedido.listarCalles()).thenReturn(calles);
    }

    private Calles whenListoLasCalles() {
        return servicioPedido.listarCalles();
    }

    private void thenMeDevuelveLasCallesYLocalidades(Calles calles) {
        assertThat(calles).isNotNull();
        assertThat(calles.getCalles()).hasSize(3);
    }

    private void givenUnaCalleYUnaAlturaFueraDelRango() {
        Routes ruta = new Routes();
        Ubicacion ubicacion = new Ubicacion();
        ruta.setDistance(4500.0);
        when(repositorioPedido.obtenerLatitudLongitud(CALLE,ALTURA,LOCALIDAD)).thenReturn(ubicacion);
        when(repositorioPedido.consultarDistanciaDelViaje(anyObject())).thenReturn(ruta);
    }

    private void givenUnaCalleYUnaAlturaDentroDelRango() {
        Routes ruta = new Routes();
        ruta.setDistance(3000.0);
        Localidades localidades = new Localidades();
        Localidad localidad = new Localidad();
        localidad.setNombre("SAN JUSTO");
        localidades.getLocalidades().add(localidad);
        when(repositorioPedido.obtenerLocalidad(anyString())).thenReturn(localidades);
        when(repositorioPedido.obtenerLatitudLongitud(CALLE,ALTURA,LOCALIDAD)).thenReturn(new Ubicacion());
        when(repositorioPedido.consultarDistanciaDelViaje(anyObject())).thenReturn(ruta);

    }

    private void whenConsultoElRango() throws DireccionInexistente {
        servicioPedido.consultarRango(CALLE, ALTURA, LOCALIDAD);
    }

    private void thenPuedoAgregarLosProductos() {
        //si no lanza la excepcion, funciona :)
    }

}
