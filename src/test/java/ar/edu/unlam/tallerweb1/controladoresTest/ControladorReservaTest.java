package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosReserva;
import ar.edu.unlam.tallerweb1.Excepciones.CantidadComensalesInvalida;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.controladores.ControladorReserva;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

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
    public void queNoSePuedaRealizarUnaReservaDeManeraExitosa() throws ReservaException, ParseException {
        givenUnaReServa();
        ModelAndView mav = whenSeConfirmaLaReserva();
        thenLaReservaNoSeRealizaConExito(mav);
    }

    @Test
    public void queSeConsultaPorUnaFechaYUnaHoraYHayDisponibilidad() throws ParseException {
        givenUnaFechaConDisponibilidad();
        ModelAndView mav = whenConsultoLaDisponibilidad();
        thenLaListaDeHorariosContieneElHorarioAReservar(mav, "22:00");
    }

    @Test
    public void queSiSeIngresaUnaCantidadDeComensalesInvalidoMeDevuelveMensajeDeCantidadDeComensalesInvalido() throws ParseException {
        givenUnaCantidadDeComensalesInvalida();
        ModelAndView mav = whenConsultoLaDisponibilidad();
        thenObtengoElMensaje("La Cantidad de comensales debe ser mayor a cero.", mav);
    }

    private void givenUnaCantidadDeComensalesInvalida() throws ParseException {
        when(servicioReserva.consultarDisponibilidad(any(),any(),any())).thenThrow(CantidadComensalesInvalida.class);
    }

    private void thenObtengoElMensaje(String mensaje, ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("reservaMesa");
        assertThat(mav.getModel().get("mnsjCantidadComensalesInvalida")).isEqualTo(mensaje);
    }

    private void givenUnaFechaConDisponibilidad() throws ParseException {
        List<String> horariosDisponibles = Arrays.asList("12:00","14:00","16:00","18:00","20:00", "22:00");
        when(servicioReserva.consultarDisponibilidad(any(),any(),anyInt())).thenReturn(horariosDisponibles);
    }

    @Test
    public void queSeConsultaPorUnaFechaYUnaHoraYNoHayDisponibilidad() throws ParseException {
        givenUnaFechaYUnaHoraSinDisponibilidad();
        ModelAndView mav= whenConsultoLaDisponibilidad();
        thenObtengoListaHorariosSinLaHoraAReservarYElMensajeDeAviso(mav, "22:00");
    }

    private void givenUnaReServa() throws ReservaException, ParseException {
        doThrow(ReservaException.class).when(servicioReserva).confirmarReserva(anyObject());
    }

    private void givenUnaFechaYUnaHoraSinDisponibilidad() throws ParseException {
        List<String> horariosDisponibles = Arrays.asList("12:00","14:00","16:00","18:00","20:00");
        when(servicioReserva.consultarDisponibilidad(any(), any(), any())).thenReturn(horariosDisponibles);
    }

    private ModelAndView whenConsultoLaDisponibilidad() {
        return controladorReserva.consultarDisponibilidad("22/10/2021", "22:00",10);
    }

    private ModelAndView whenSeConfirmaLaReserva() {
        DatosReserva datos = new DatosReserva();
        datos.setCantidadComensales(2);
        datos.setFecha("2001-02-09");
        datos.setHora("22:00");
        datos.setCelular("1133333333");
        datos.setNombre("Taller");
        return controladorReserva.confirmarReserva(datos);
    }

    private void thenObtengoListaHorariosSinLaHoraAReservarYElMensajeDeAviso(ModelAndView mav, String horarioAReservar) {
        assertThat(mav.getViewName()).isEqualTo("reservaMesa");
        assertThat(mav.getModel().get("reservaNoDisponible")).isEqualTo("No hay disponibilidad para la Fecha y Hora Especificada. Por favor consulte en otro horario");
        List<String> horariosDisponibles = (List<String>) mav.getModel().get("horariosDisponibles");
        assertThat(horariosDisponibles).doesNotContain(horarioAReservar);
    }

    private void thenLaReservaNoSeRealizaConExito(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("redirect:reservar?confirmada=false");
        //assertThat(mav.getModel().get("mnsj")).isEqualTo("No se ha podido realizar la Reserva.");
    }

    private void thenLaReservaSeRealizaConExito(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("redirect:reservar?confirmada=true");
       // assertThat(mav.getModel().get("mnsj")).isEqualTo("La Reserva se ha realizado con Exito!");
    }

    private void thenLaListaDeHorariosContieneElHorarioAReservar(ModelAndView mav, String horarioAReservar) {
        assertThat(mav.getViewName()).isEqualTo("reservaMesa");
        assertThat(mav.getModel().get("reservaDisponible")).isEqualTo("Existe disponibilidad para la Fecha y Hora Especificada. Complete los datos y confirme la Reserva.");
        List<String> horariosDisponibles = (List<String>) mav.getModel().get("horariosDisponibles");
        assertThat(horariosDisponibles).contains(horarioAReservar);
    }
}
