package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioReservaTest extends SpringTest {

    private static final Reserva RESERVA = new Reserva();
    private static final Date FECHA_CONSULTADA = new Date();
    @Autowired
    private RepositorioReserva repositorioReserva;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaGuardarUnaReserva(){
        Reserva reserva = givenUnaReserva();
        Long id = whenGuardoLaReserva(reserva);
        thenObtengoElIdGenerado(id);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnaReservaPorId(){
        Long id = givenUnaReservaGuardada(RESERVA);
        Reserva reservaRecuperada = whenBuscoUnaReservaPorId(id);
        thenObtengoLaReservaDeseada(reservaRecuperada);
    }

    @Test
    @Rollback
    @Transactional
    public void queSiHayDisponibilidadDevuelvaDevuelveUnaListaVacia() throws ParseException {
        givenUnaCantidadDeReservasCargadas();
        List<Reserva> reservas = whenConsultoMesasReservadasPorFechaYHora(FECHA_CONSULTADA, "22:00");
        thenLaListaDeReservasEstaVacia(reservas);
    }

    @Test
    @Rollback
    @Transactional
    public void queSiHayReservasRealizadasParaLaFechaYLaHoraEspecificadaDevuelveUnaListaConReservas() throws ParseException {
        givenUnaCantidadDeReservasCargadas();
        List<Reserva> reservas = whenConsultoMesasReservadasPorFechaYHora(this.pasarFechaDeStringADate("22/10/2021"), "22:00");
        thenLaCantidadDeMesasEs(reservas);
    }

    private void thenLaCantidadDeMesasEs(List<Reserva> cantidadMesasReservadas) {
        assertThat(cantidadMesasReservadas.size()).isEqualTo(3);
    }

    private void thenObtengoLaReservaDeseada(Reserva reservaRecuperada) {
        assertThat(reservaRecuperada.getNombre()).isEqualTo("Bon-appetit");
    }

    private void thenLaListaDeReservasEstaVacia(List<Reserva> reservas) {
        assertThat(reservas.size()).isEqualTo(0);
    }

    private List<Reserva> whenConsultoMesasReservadasPorFechaYHora(Date fecha, String hora) {
        return repositorioReserva.obtenerReservasPor(fecha,hora);
    }

    private void givenUnaCantidadDeReservasCargadas() throws ParseException {
        List<Reserva> reservas = Arrays.asList(new Reserva(), new Reserva(), new Reserva());
        Date fecha = this.pasarFechaDeStringADate("22/10/2021");
        String hora = "22:00";
        Integer cantMesas = 10;
        for(Reserva reserva : reservas){
            reserva.setFecha(fecha);
            reserva.setHora(hora);
            reserva.setMesas(cantMesas);
            session().save(reserva);
        }
    }

    private Long givenUnaReservaGuardada(Reserva reserva) {
        reserva.setNombre("Bon-appetit");
        return repositorioReserva.guardarReserva(reserva);
    }

    private Reserva whenBuscoUnaReservaPorId(Long id) {
        return repositorioReserva.buscarPorId(id);
    }

    private Reserva givenUnaReserva() {
        RESERVA.setFecha(new Date());
        RESERVA.setNombre("Rodrigo");
        RESERVA.setMesas(8);
        return RESERVA;
    }

    private Long whenGuardoLaReserva(Reserva reserva) {
        return repositorioReserva.guardarReserva(reserva);
    }

    private void thenObtengoElIdGenerado(Long id) {
        assertThat(id).isNotNull();
    }

    private Date pasarFechaDeStringADate(String fecha) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(fecha);
    }
}
