package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.Excepciones.ProductoNoEncontrado;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.controladores.ControladorReserva;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ControladorReservaTest {

    private ServicioReserva servicioReserva = mock(ServicioReserva.class);
    private ControladorReserva controladorReserva = new ControladorReserva(servicioReserva);

    @Test
    public void queSePuedaRealizarUnaReservaConExito(){
        ModelAndView mav = whenSeConfirmaLaReserva();
        thenLaReservaSeRealizaConExito(mav);
    }

    @Test
    public void queNoSePuedaRealizarUnaReservaDeManeraExitosa() throws ReservaException {
        givenUnaReServa();
        ModelAndView mav = whenSeConfirmaLaReserva();
        thenLaReservaNoSeRealizaConExito(mav);
    }

    private void givenUnaReServa() throws ReservaException {
        doThrow(ReservaException.class).when(servicioReserva).confirmarReserva(anyObject());
    }

    private ModelAndView whenSeConfirmaLaReserva() {
        return controladorReserva.confirmarReserva();
    }

    private void thenLaReservaNoSeRealizaConExito(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("reserva");
        assertThat(mav.getModel().get("mnsj")).isEqualTo("No se ha podido realizar la Reserva.");
    }

    private void thenLaReservaSeRealizaConExito(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("reserva");
        assertThat(mav.getModel().get("mnsj")).isEqualTo("La Reserva se ha realizado con Exito!");
    }
}
