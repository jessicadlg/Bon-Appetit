package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosConfirmacion;
import ar.edu.unlam.tallerweb1.Excepciones.DireccionInexistente;
import ar.edu.unlam.tallerweb1.Excepciones.PedidoInexistente;
import ar.edu.unlam.tallerweb1.Excepciones.PedidoVacio;
import ar.edu.unlam.tallerweb1.Excepciones.RangoInvalido;
import ar.edu.unlam.tallerweb1.controladores.ControladorPedido;
import ar.edu.unlam.tallerweb1.modelo.ItemPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCategoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorPedidoTest {

    private ModelAndView mav;
    private ServicioPedido servicioPedido;
    private ServicioProducto servicioProducto;
    private ServicioCategoria servicioCategoria;
    private ControladorPedido controladorPedido;
    private Long idProducto = 1L;
    private Long idPedido = 2L;
    private final String CALLE = "Estrada";
    private final String ALTURA = "123";
    private String LOCALIDAD = "06427010014";

    private DatosConfirmacion datosConfirmacion = new DatosConfirmacion("Calle","Altura","Localidad","Nombre","telefono","400.0","60.0");
    private DatosConfirmacion datosConfirmacionIncompletoNombre = new DatosConfirmacion("Calle","Altura","Localidad",null,"telefono","400.0","60.0");
    private DatosConfirmacion datosConfirmacionIncompletoTelefono = new DatosConfirmacion("Calle","Altura","Localidad","Nombre",null,"400.0","60.0");

    @Before
    public void init() {
        servicioPedido = mock(ServicioPedido.class);
        servicioProducto = mock(ServicioProducto.class);
        servicioCategoria= mock(ServicioCategoria.class);
        controladorPedido = new ControladorPedido(servicioPedido,servicioProducto,servicioCategoria);
    }

    @Test
    public void quePuedaConsultarSiEstaDentroDelRangoDeEnvios(){
        givenUnaCalleYUnaAlturaDentroDelRango();

        whenConsultoElRango();

        thenPuedoAgregarLosProductos();
    }
    @Test
    public void quePuedaConsultarSiEstaFueraDelRangoDeEnvios() throws DireccionInexistente {

        givenUnaCalleYUnaAlturaFueraDelRango();

        whenConsultoElRango();

        thenMeAvisaQueEstoyFueraDelRango();

    }

    @Test
    public void queSiIngresoUnaDireccionInvalidaMandeElMsj() throws DireccionInexistente {

        givenQueIngresoUnaDireccionInvalida();

        whenConsultoElRango();

        thenMeMandaElMsjDeDireccionInexistente();

    }

    @Test
    public void queNoPermitaConsultarRangoSiNoIngresoUnaCalle(){
        whenConsultoElRangoSinPonerLaCalle();

        thenNoMeDejaConsultarElRangoPorLaCalle();
    }

    @Test
    public void  queNoPermitaConsultarRangoSiNoIngresoLaAltura(){
        whenConsultoElRangoSinPonerLaAltura();

        thenNoMeDejaConsultarElRangoPorLaAltura();
    }

    @Test
    public void queNoPermitaConsultarRangoSiIngresoUnaAlturaNegativa(){

        whenConsultoElRangoConUnaAlturaNegativa();

        thenNoMeDejaConsultarElRangoPorLaAlturaNegativa();
    }

    @Test
    public void queNoPermitaConsultarRangoSiNoIngresoLaLocalidad(){
        whenConsultoElRangoSinPonerLaLocalidad();

        thenNoMeDejaConsultarElRangoPorLaLocalidad();
    }

    @Test
    public void queSePuedaGenerarUnPedido(){

        givenUnPedidoNuevo();

        whenQuieroGenerarElPedido();

        thenObtengoElIdDelPedidoGenerado();

    }

    @Test
    public void queCuandoIngresaAIniciarPedidoNosDeUnMensajeQueSuPedidoEstaVacio(){

        givenQueInicioUnPedidoNuevo();

        whenSeMuestraElPedido();

        thenMeInformaQueSeEncuentraVacio();
    }

    @Test
    public void queSePuedaAgregarUnProductoAUnPedidoYQueSeCalculeElTotal(){

        givenQueExisteUnPedidoSinProductos();

        whenAgregoUnProducto();

        thenMeDevuelveElPedidoConSusProductos();

    }

    @Test
    public void queSePuedaConfirmarLosProductosDeUnPedido(){

        givenQueExisteUnPedidoConProductos();

        whenConfirmoElPedido();

        thenObtengoELTiempoDeDemoraDelPedido();

    }

    @Test
    public void queSiConfirmoLosProductosDeUnPedidoInexistenteMeMandeElMsj(){

        givenQueSeConfirmaUnPedidoInexistente();

        whenConfirmoElPedido();

        thenMeMandaElMsjDePedidoInexistente();

    }

    @Test
    public void queAlConfirmarUnaCompraSinIngresarSuNombreMeMandeElMsj(){

        givenQueSeConfirmaUnaCompraSinHaberIngresadoElNombre();

        whenConfirmoLaCompra(datosConfirmacionIncompletoNombre);

        thenMeMandaElMsjDeNombreIncompleto();

    }

    @Test
    public void queAlConfirmarUnaCompraSinIngresarSuTelefonoMeMandeElMsj(){

        givenQueSeConfirmaUnaCompraSinHaberIngresadoElTelefono();

        whenConfirmoLaCompra(datosConfirmacionIncompletoTelefono);

        thenMeMandaElMsjDeTelefonoIncompleto();

    }

    @Test
    public void queSePuedaGenerarUnaCompraExitosa(){

        givenQueExisteUnaCompraExitosa();

        whenConfirmoLaCompra(datosConfirmacion);

        thenMeDiceQueLaCompraEsExitosa();

    }

    private void givenQueExisteUnaCompraExitosa() {    }

    private void thenMeDiceQueLaCompraEsExitosa() {
        assertThat(mav.getViewName()).isEqualTo("redirect:compra-exitosa");
    }

    private void givenQueSeConfirmaUnaCompraSinHaberIngresadoElTelefono() {
    }

    private void thenMeMandaElMsjDeTelefonoIncompleto() {
        Map<String,String> errores = (Map<String, String>) mav.getModel().get("validacionesCompra");
        assertThat(mav.getViewName()).isEqualTo("formularioPedido");
        assertThat(errores.get("telefonoError")).isEqualTo("Por favor ingrese un número de telefono");
    }

    private void givenQueSeConfirmaUnaCompraSinHaberIngresadoElNombre() {
    }

    private void whenConfirmoLaCompra(DatosConfirmacion datosConfirmacion) {
        mav = controladorPedido.procesarCompra(datosConfirmacion);
    }

    private void thenMeMandaElMsjDeNombreIncompleto() {
        Map<String,String> errores = (Map<String, String>) mav.getModel().get("validacionesCompra");
        assertThat(mav.getViewName()).isEqualTo("formularioPedido");
        assertThat(errores.get("nombreError")).isEqualTo("Por favor ingrese su nombre");
    }

    private void givenQueSeConfirmaUnPedidoInexistente() {
        when(servicioPedido.obtenerPedido(anyLong())).thenThrow(PedidoInexistente.class);
    }

    private void thenMeMandaElMsjDePedidoInexistente() {
        assertThat(mav.getViewName()).isEqualTo("formularioPedido");
        assertThat(mav.getModel().get("pedidoInexistente")).isEqualTo("Este pedido no existe");
    }

    private void givenQueExisteUnPedidoConProductos() {
        Pedido pedido = new Pedido();
        List<ItemPedido> listaPedidos = new ArrayList<>();
        Producto p1 = new Producto();
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProducto(p1);
        listaPedidos.add(itemPedido);
        itemPedido.setPedido(pedido);
        when(servicioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(servicioPedido.obtenerItemsPedido(anyLong())).thenReturn(listaPedidos);
    }

    private void whenConfirmoElPedido() {
        mav = controladorPedido.irAformularioConfirmacion(idPedido);
    }

    private void thenObtengoELTiempoDeDemoraDelPedido() {
        assertThat(mav.getViewName()).isEqualTo("formularioPedido");
        assertThat(mav.getModel().get("pedido")).isEqualTo(servicioPedido.obtenerPedido(idPedido));
        assertThat(mav.getModel().get("itemsPedido")).isEqualTo(servicioPedido.obtenerItemsPedido(idPedido));
    }
    private void givenQueExisteUnPedidoSinProductos() {
        when(servicioPedido.agregarProductoAlPedido(idProducto,idPedido)).thenReturn(new Pedido());
    }

    private void whenAgregoUnProducto() {
        mav = controladorPedido.agregarProductoAlPedido(idProducto,idPedido);
    }

    private void thenMeDevuelveElPedidoConSusProductos() {
        assertThat(mav.getViewName()).isEqualTo("redirect:carrito?idPedido=2");
    }

    private void givenQueInicioUnPedidoNuevo() {
        when(servicioPedido.obtenerPedido(idPedido)).thenThrow(PedidoVacio.class);
    }

    private void whenSeMuestraElPedido() {
        mav= controladorPedido.mostrarPedido(idPedido);
    }

    private void thenMeInformaQueSeEncuentraVacio() {
        assertThat(mav.getViewName()).isEqualTo("productos");
        assertThat(mav.getModel().get("pedidoVacio")).isEqualTo("Su pedido está vacio");
    }

    private void givenUnPedidoNuevo() {
        when(servicioPedido.generarPedido()).thenReturn(1L);
    }

    private void whenQuieroGenerarElPedido() {
        mav = controladorPedido.generarPedido();
    }

    private void thenObtengoElIdDelPedidoGenerado() {
        assertThat(mav.getViewName()).isEqualTo("redirect:pedido?idPedido=1");
    }

    private void whenConsultoElRangoSinPonerLaLocalidad() {
        mav = controladorPedido.consultarRango("Calle falsa","8888",null);
    }

    private void thenNoMeDejaConsultarElRangoPorLaLocalidad() {
        Map<String,String> errores = (Map<String, String>) mav.getModel().get("validacionesRango");
        assertThat(mav.getViewName()).isEqualTo("formularioConsultaRango");
        assertThat(errores.get("localidadError")).isEqualTo("Ingrese una localidad");
    }

    private void whenConsultoElRangoConUnaAlturaNegativa() {
        mav = controladorPedido.consultarRango("Calle falsa","-7","0213213");

    }

    private void thenNoMeDejaConsultarElRangoPorLaAlturaNegativa() {
        Map<String,String> errores = (Map<String, String>) mav.getModel().get("validacionesRango");
        assertThat(mav.getViewName()).isEqualTo("formularioConsultaRango");
        assertThat(errores.get("alturaError")).isEqualTo("La altura debe ser mayor a cero");
    }

    private void whenConsultoElRangoSinPonerLaAltura() {
        mav = controladorPedido.consultarRango("Calle falsa",null,"0213213");
    }

    private void thenNoMeDejaConsultarElRangoPorLaAltura() {
        Map<String,String> errores = (Map<String, String>) mav.getModel().get("validacionesRango");
        assertThat(mav.getViewName()).isEqualTo("formularioConsultaRango");
        assertThat(errores.get("alturaError")).isEqualTo("Ingrese una altura");
    }

    private void whenConsultoElRangoSinPonerLaCalle() {
        mav = controladorPedido.consultarRango(null,"8821","0213213");
    }

    private void thenNoMeDejaConsultarElRangoPorLaCalle() {
        Map<String,String> errores = (Map<String, String>) mav.getModel().get("validacionesRango");
        assertThat(mav.getViewName()).isEqualTo("formularioConsultaRango");
        assertThat(errores.get("calleError")).isEqualTo("Ingrese una calle");
    }

    private void givenQueIngresoUnaDireccionInvalida() throws DireccionInexistente {
        when(servicioPedido.consultarRango(anyString(),anyString(),anyString())).thenThrow(DireccionInexistente.class);
    }

    private void thenMeMandaElMsjDeDireccionInexistente() {
        assertThat(mav.getViewName()).isEqualTo("formularioConsultaRango");
        assertThat(mav.getModel().get("errorConsultaRango")).isEqualTo("Direccion inexistente, por favor vuelva a intentar");
    }

    private void givenUnaCalleYUnaAlturaFueraDelRango() throws DireccionInexistente {
        doThrow(RangoInvalido.class).when(servicioPedido).consultarRango(CALLE,ALTURA,LOCALIDAD);
    }

    private void thenMeAvisaQueEstoyFueraDelRango() {
        assertThat(mav.getViewName()).isEqualTo("redirect:consultaRangoError");
    }

    private void givenUnaCalleYUnaAlturaDentroDelRango() {    }

    private void whenConsultoElRango() {
        mav = controladorPedido.consultarRango(CALLE,ALTURA, LOCALIDAD);
    }

    private void thenPuedoAgregarLosProductos() {
        assertThat(mav.getViewName()).isEqualTo("redirect:generar-pedido");
    }

}
