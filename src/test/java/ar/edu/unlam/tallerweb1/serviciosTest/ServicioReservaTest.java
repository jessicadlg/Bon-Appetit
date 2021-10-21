package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReservaImpl;
import org.junit.Test;

import static org.mockito.Matchers.anyLong;
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

    private void givenUnaReserva() {
        when(repositorioReserva.guardarReserva(anyObject())).thenReturn(null);
    }

    private void whenSeConfirmaLaReserva() throws ReservaException {
        servicioReserva.confirmarReserva(new Reserva());
    }
}
