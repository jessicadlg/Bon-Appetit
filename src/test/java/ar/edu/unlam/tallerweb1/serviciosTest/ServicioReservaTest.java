package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaNoDisponible;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReservaImpl;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioReservaTest {

    private RepositorioReserva repositorioReserva = mock(RepositorioReserva.class);
    private ServicioReserva servicioReserva = new ServicioReservaImpl(repositorioReserva);

    @Test(expected = ReservaException.class)
    public void queCuandoUnaReservaNoEsExitosaLanceReservaException() throws ReservaException {
        givenUnaReserva();
        whenSeConfirmaLaReserva();
    }

    @Test
    public void queHayDisponibilidadParaUnaFechaDeterminada() throws ParseException {
        givenUnaFecha();
        List<Reserva> reservas = whenSeConsultaLaDisponibilidad();
        thenHayMesasDisponibles(reservas);
    }

    @Test(expected = ReservaNoDisponible.class)
    public void queSiNoHayDisponibilidadLanceRservaNoDisponible() throws ParseException {
        givenUnaFechaSinDisponibilidad();
        whenSeConsultaLaDisponibilidad();
    }

    private void givenUnaFechaSinDisponibilidad() {
        when(repositorioReserva.buscarMesasPorFecha(any())).thenReturn(new ArrayList<Reserva>());
    }

    private void thenHayMesasDisponibles(List<Reserva> reservas) {
        assertThat(reservas).hasSize(3);
    }

    private void givenUnaFecha() {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        reservas.add(new Reserva());
        reservas.add(new Reserva());
        reservas.add(new Reserva());
        when(repositorioReserva.buscarMesasPorFecha(any())).thenReturn(reservas);
    }

    private List<Reserva> whenSeConsultaLaDisponibilidad() throws ParseException {
        return servicioReserva.consultarDisponibilidad("22/10/2021", 8);
    }

    private void givenUnaReserva() {
        when(repositorioReserva.guardarReserva(anyObject())).thenReturn(null);
    }

    private void whenSeConfirmaLaReserva() throws ReservaException {
        servicioReserva.confirmarReserva(new Reserva());
    }
}
