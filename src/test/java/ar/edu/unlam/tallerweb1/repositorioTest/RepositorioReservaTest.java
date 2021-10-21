package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioReservaTest extends SpringTest {

    private static final Reserva RESERVA = new Reserva();
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

    private void thenObtengoLaReservaDeseada(Reserva reservaRecuperada) {
        assertThat(reservaRecuperada.getNombre()).isEqualTo("Bon-appetit");
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
}
