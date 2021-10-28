package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosReserva;
import ar.edu.unlam.tallerweb1.Excepciones.CantidadComensalesInvalida;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReservaImpl;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioReservaTest {

    private RepositorioReserva repositorioReserva = mock(RepositorioReserva.class);
    private ServicioReserva servicioReserva = new ServicioReservaImpl(repositorioReserva);

    @Test(expected = ReservaException.class)
    public void queCuandoUnaReservaNoEsExitosaLanceReservaException() throws ReservaException, ParseException {
        givenUnaReserva();
        whenSeConfirmaLaReserva();
    }

    @Test
    public void queHayDisponibilidadParaUnaFechaYHorarioDeterminado() throws ParseException {
        givenUnaFechaYUnHorarioConDisponibilidad();
        List<String> horariosDisponibles = whenSeConsultaLaDisponibilidad();
        thenLaListaDeHorariosContieneElHorarioEnElQueSeDeseaRealizarLaReserva(horariosDisponibles, "22:00");
    }

    @Test
    public void queSiNoHayDisponibilidadDevuelvaLaListaDeHorariosDisponibles() throws ParseException {
        givenUnaFechaYUnHorarioSinDisponibilidad();
        List<String> horariosDisponibles = whenSeConsultaLaDisponibilidad();
        thenLaListaDeHorariosNoContieneElHorario(horariosDisponibles,"21:00");
    }

    @Test(expected = CantidadComensalesInvalida.class)
    public void queSiLaCantidadDeComensalesEsInvalidaLanceExcepcion() throws ParseException {
        Integer cantidadComensales = givenUnaCantidadDeComensalesInvalida();
        whenSeConsultaLaDisponibilidadConUnaCantidadInvalidaDeComensales(cantidadComensales);
    }

    private void whenSeConsultaLaDisponibilidadConUnaCantidadInvalidaDeComensales(Integer cantidadComensales) throws ParseException {
        servicioReserva.consultarDisponibilidad("24/10/2021","22:00",cantidadComensales);
    }

    private Integer givenUnaCantidadDeComensalesInvalida() {
        return 0;
    }

    private void thenLaListaDeHorariosNoContieneElHorario(List<String> horariosDisponibles, String horarioAReservar) {
        assertThat(horariosDisponibles).doesNotContain(horarioAReservar);
    }

    private void givenUnaFechaYUnHorarioSinDisponibilidad() {
        when(repositorioReserva.obtenerMesasReservadasPor(any(), any())).thenReturn(30L);
    }

    private void thenLaListaDeHorariosContieneElHorarioEnElQueSeDeseaRealizarLaReserva(List<String> horarios, String horarioAReservar) {
        assertThat(horarios).contains(horarioAReservar);
    }

    private void givenUnaFechaYUnHorarioConDisponibilidad() {
        when(repositorioReserva.obtenerMesasReservadasPor(any(), any())).thenReturn(null);
    }

    private List<String> whenSeConsultaLaDisponibilidad() throws ParseException {
        return servicioReserva.consultarDisponibilidad("2001-10-20", "22:00", 8);
    }

    private void givenUnaReserva() {
        when(repositorioReserva.guardarReserva(anyObject())).thenReturn(null);
    }

    private void whenSeConfirmaLaReserva() throws ReservaException, ParseException {
        DatosReserva datos = new DatosReserva();
        datos.setCantidadComensales(2);
        datos.setFecha("2001-02-09");
        datos.setHora("22:00");
        datos.setCelular("1133333333");
        datos.setNombre("Taller");
        servicioReserva.confirmarReserva(datos);
    }
}
