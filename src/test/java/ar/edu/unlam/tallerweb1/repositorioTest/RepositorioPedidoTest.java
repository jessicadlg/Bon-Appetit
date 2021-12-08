package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioPedidoTest extends SpringTest {

    @Autowired
    private RepositorioPedido repositorioPedido;
    private Ubicacion ubicacion;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUnPedidoPorId(){

        Long idPedido = givenQueExisteUnPedido();

        Pedido pedidoObtenido = whenBuscoUnPedidoPorId(idPedido);

        thenMeDevuelveElPedidoBuscado(idPedido, pedidoObtenido);

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerLatitudYLongitud(){

        whenBuscoLaLatidudYLongitud();

        thenMeDevuelveLaUbicacion();

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerLaDistanciaYEltiempoDeUnViaje(){
        Ubicacion ubicacion = givenUnaUbicacio();
        Routes ruta = whenConsultoLaDistanciaYElTiempo(ubicacion);
        thenLaDistanciaEsYElTiempoDelViajeEs(4578.7,343.3/60, ruta);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedanListarTodasLasCalles(){

        Calles calles = whenListoLasCalles();

        thenMeDevuelveLasCalles(calles);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnaLocalidadPorId(){

       Localidades localidad =  whenBuscoUnaLocalidad();

       thenObtengoElNombreDeLaLocalidad(localidad);

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedanListaTodosLosPedidos(){

        List<Pedido> listaEsperada = givenQueExisteUnaListaDePedidos();

        List<Pedido> listaObtenida = whenListoLosPedidos();

        thenMeDevuelveTodosLosPedidos(listaEsperada,listaObtenida);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedanListarPedidosPorUnEstadoEspecifico(){

        List<Pedido> listaEsperada = givenQueExisteUnaListaDePedidosConEstados();

        List<Pedido> listaObtenida = whenListoLosPedidosPorUnEstadoEspecifico();

        thenMeDevuelveTodosLosPedidosConEseEstadoEspecifico(listaEsperada,listaObtenida);

    }

    private List<Pedido> givenQueExisteUnaListaDePedidosConEstados() {
        List<Pedido> listaPedidos = new ArrayList<>();
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        Pedido p3 = new Pedido();
        Pedido p4 = new Pedido();
        Pedido p5 = new Pedido();
        Pedido p6 = new Pedido();
        p1.setEstadoPedido(EstadoPedido.PREPARANDO);
        p2.setEstadoPedido(EstadoPedido.PREPARANDO);
        p3.setEstadoPedido(EstadoPedido.PREPARANDO);
        p4.setEstadoPedido(EstadoPedido.FINALIZADO);
        p5.setEstadoPedido(EstadoPedido.VIAJANDO);
        p6.setEstadoPedido(EstadoPedido.VIAJANDO);
        session().save(p1);
        session().save(p2);
        session().save(p3);
        session().save(p4);
        session().save(p5);
        session().save(p6);
        listaPedidos.add(p1);
        listaPedidos.add(p2);
        listaPedidos.add(p3);
        return listaPedidos;
    }


    private List<Pedido> whenListoLosPedidosPorUnEstadoEspecifico() {
        return repositorioPedido.listarPedidoPorEstado(EstadoPedido.PREPARANDO);
    }

    private void thenMeDevuelveTodosLosPedidosConEseEstadoEspecifico(List<Pedido> listaEsperada, List<Pedido> listaObtenida) {
        assertThat(listaObtenida).isNotNull();
        assertThat(listaEsperada).isEqualTo(listaObtenida);
        assertThat(listaObtenida).hasSize(3);
    }

    private List<Pedido> givenQueExisteUnaListaDePedidos() {
        List<Pedido> listaPedidos = new ArrayList<>();
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        Pedido p3 = new Pedido();
        session().save(p1);
        session().save(p2);
        session().save(p3);
        listaPedidos.add(p1);
        listaPedidos.add(p2);
        listaPedidos.add(p3);
        return listaPedidos;
    }

    private List<Pedido> whenListoLosPedidos() {
        return repositorioPedido.listarPedidos();
    }

    private void thenMeDevuelveTodosLosPedidos(List<Pedido> listaEsperada, List<Pedido> listaObtenida) {
        assertThat(listaObtenida).isNotNull();
        assertThat(listaObtenida).isEqualTo(listaEsperada);
        assertThat(listaObtenida).hasSize(3);
    }


    private Localidades whenBuscoUnaLocalidad() {
        return repositorioPedido.obtenerLocalidad("6427010010");
    }

    private void thenObtengoElNombreDeLaLocalidad(Localidades localidad) {
        assertThat(localidad).isNotNull();
        assertThat(localidad.getLocalidades().get(0).getNombre()).isEqualTo("SAN JUSTO");
    }

    private Calles whenListoLasCalles() {
        return  repositorioPedido.listarCalles();
    }

    private void thenMeDevuelveLasCalles(Calles calles) {
        assertThat(calles).isNotNull();
        assertThat(calles.getCalles().get(0).getNombre()).isEqualTo("1 DE MAYO");
    }

    private Ubicacion givenUnaUbicacio() {
        Ubicacion ubicacion1 = new Ubicacion();
        ubicacion1.setLon(-58.596913157319044);
        ubicacion1.setLat(-34.68837769963411);
        return ubicacion1;
    }

    private Routes whenConsultoLaDistanciaYElTiempo(Ubicacion ubicacion) {
        return repositorioPedido.consultarDistanciaDelViaje(ubicacion);
    }

    private void thenLaDistanciaEsYElTiempoDelViajeEs(Double distancia, Double duracion, Routes ruta) {
        assertThat(ruta.getDistance()).isEqualTo(distancia);
        assertThat(ruta.getDuration()).isEqualTo(duracion);
    }

    private void whenBuscoLaLatidudYLongitud() {
        ubicacion = repositorioPedido.obtenerLatitudLongitud("Jose Ignacio Rucci","592","06427010014");
    }

    private void thenMeDevuelveLaUbicacion() {
        assertThat(ubicacion).isNotNull();
        assertThat(ubicacion.getLat()).isEqualTo(-34.68837769963411);
        assertThat(ubicacion.getLon()).isEqualTo(-58.596913157319044);
    }

    private Long givenQueExisteUnPedido() {
        Pedido p1 = new Pedido();
        return (Long) session().save(p1);
    }

    private Pedido whenBuscoUnPedidoPorId(Long idPedido) {
        return repositorioPedido.obtenerPedido(idPedido);
    }

    private void thenMeDevuelveElPedidoBuscado(Long idPedido, Pedido pedidoObtenido) {
        assertThat(pedidoObtenido).isNotNull();
        assertThat(idPedido).isEqualTo(pedidoObtenido.getId());
    }
}
