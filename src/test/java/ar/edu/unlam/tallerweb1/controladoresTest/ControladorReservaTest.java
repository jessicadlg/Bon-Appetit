package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaNoDisponible;
import ar.edu.unlam.tallerweb1.controladores.ControladorReserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

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

    @Test
    public void queSeConsultaPorUnaFechaYNoHayDisponibilidad() throws ParseException {
        givenUnaFechaYCantidadComensales();
        ModelAndView mav= whenConsultoLaDisponibilidad();
        thenMeDevuelveMensaje(mav);
    }

    private void givenUnaFechaYCantidadComensales() throws ParseException {
        when(servicioReserva.consultarDisponibilidad(any(), anyInt())).thenThrow(ReservaNoDisponible.class);
    }

    private ModelAndView whenConsultoLaDisponibilidad() {
        return controladorReserva.consultarDisponibilidad("22/10/2021", 10);
    }

    private void thenMeDevuelveMensaje(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("reservarMesa");
        assertThat(mav.getModel().get("reservaNoDisponible")).isEqualTo("No hay disponibilidad para la Fecha Especificada.");
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
