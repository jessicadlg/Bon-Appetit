package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

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

    private void whenBuscoLaLatidudYLongitud() {
        ubicacion = repositorioPedido.obtenerLatitudLongitud("Jose Ignacio Rucci","592","06427010014");
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
        System.out.println(calles);
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
