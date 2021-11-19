package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.Excepciones.RangoInvalido;
import ar.edu.unlam.tallerweb1.controladores.ControladorPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioCategoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

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

    private void givenUnaCalleYUnaAlturaDentroDelRango() {

    }

    private void whenConsultoElRango() {
        mav = controladorPedido.consultarRango(CALLE,ALTURA, LOCALIDAD);
    }

    private void thenPuedoAgregarLosProductos() {
        assertThat(mav.getViewName()).isEqualTo("redirect:generar-pedido");
    }

    @Test
    public void quePuedaConsultarSiEstaFueraDelRangoDeEnvios(){

        givenUnaCalleYUnaAlturaFueraDelRango();

        whenConsultoElRango();

        thenMeAvisaQueEstoyFueraDelRango();

    }

    private void givenUnaCalleYUnaAlturaFueraDelRango() {
        doThrow(RangoInvalido.class).when(servicioPedido).consultarRango(CALLE,ALTURA,LOCALIDAD);
    }

    private void thenMeAvisaQueEstoyFueraDelRango() {
        assertThat(mav.getViewName()).isEqualTo("redirect:consultaRangoError");
    }

    @Test
    public void queSePuedaGenerarUnPedido(){

        givenUnPedidoNuevo();

        whenQuieroGenerarElPedido();

        thenObtengoElIdDelPedidoGenerado();

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

    @Test
    public void queSePuedaAgregarUnProductoAUnPedidoYQueSeCalculeElTotal(){

        givenQueExisteUnPedidoSinProductos();

        whenAgregoUnProducto();

        thenMeDevuelveElPedidoConSusProductos();

    }

    @Test
    public void queNoPermitaConsultarRangoSiNoIngresoLaLocalidad(){

        whenConsultoElRangoSinPonerLaLocalidad();

        thenNoMeDejaConsultarElRangoPorLaLocalidad();

    }

    @Test
    public void  queNoPermitaConsultarRangoSiNoIngresoLaAltura(){
        whenConsultoElRangoSinPonerLaAltura();

        thenNoMeDejaConsultarElRangoPorLaAltura();

    }

    @Test
    public void queNoPermitaConsultarRangoSiIngresoEspaciosEnBlancoConLaAltura(){

        whenConsultoElRangoPoniendoEspaciosEnBlancoConLaAltura();

        thenNoMeDejaConsultarElRangoPorLaAltura();
    }

    @Test
    public void queNoPermitaConsultarRangoSiIngresoUnaAlturaNegativa(){

        whenConsultoElRangoConUnaAlturaNegativa();

        thenNoMeDejaConsultarElRangoPorLaAlturaNegativa();
    }

    @Test
    public void queNoPermitaConsultarRangoSiNoIngresoUnaCalle(){

        whenConsultoElRangoSinPonerLaCalle();

        thenNoMeDejaConsultarElRangoPorLaCalle();


    }

    @Test
    public void queSePuedaConfirmarUnPedido(){

        givenQueExisteUnPedidoConProductos();

        whenConfirmoElPedido();

        thenObtengoELTiempoDeDemoraDelPedido();

    }

    private void givenQueExisteUnPedidoConProductos() {
    }

    private void whenConfirmoElPedido() {
    }

    private void thenObtengoELTiempoDeDemoraDelPedido() {
    }

    private void whenConsultoElRangoSinPonerLaCalle() {
        mav = controladorPedido.consultarRango(null,"8821","0213213");

    }

    private void thenNoMeDejaConsultarElRangoPorLaCalle() {
        Map<String,String> errores = (Map<String, String>) mav.getModel().get("validacionesRango");
        assertThat(mav.getViewName()).isEqualTo("formularioConsultaRango");
        assertThat(errores.get("calleError")).isEqualTo("Ingrese una calle");
    }

    private void whenConsultoElRangoConUnaAlturaNegativa() {
        mav = controladorPedido.consultarRango("Calle falsa","-7","0213213");

    }

    private void thenNoMeDejaConsultarElRangoPorLaAlturaNegativa() {
        Map<String,String> errores = (Map<String, String>) mav.getModel().get("validacionesRango");
        assertThat(mav.getViewName()).isEqualTo("formularioConsultaRango");
        assertThat(errores.get("alturaError")).isEqualTo("La altura debe ser mayor a cero");
    }

    private void whenConsultoElRangoPoniendoEspaciosEnBlancoConLaAltura() {
        mav = controladorPedido.consultarRango("Calle falsa","  ","0213213");

    }

    private void whenConsultoElRangoSinPonerLaAltura() {
        mav = controladorPedido.consultarRango("Calle falsa",null,"0213213");
    }

    private void thenNoMeDejaConsultarElRangoPorLaAltura() {
        Map<String,String> errores = (Map<String, String>) mav.getModel().get("validacionesRango");
        assertThat(mav.getViewName()).isEqualTo("formularioConsultaRango");
        assertThat(errores.get("alturaError")).isEqualTo("Ingrese una altura");
    }

    private void whenConsultoElRangoSinPonerLaLocalidad() {
        mav = controladorPedido.consultarRango("Calle falsa","8888",null);
    }


    private void thenNoMeDejaConsultarElRangoPorLaLocalidad() {
        Map<String,String> errores = (Map<String, String>) mav.getModel().get("validacionesRango");
        assertThat(mav.getViewName()).isEqualTo("formularioConsultaRango");
        assertThat(errores.get("localidadError")).isEqualTo("Ingrese una localidad");
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








}
