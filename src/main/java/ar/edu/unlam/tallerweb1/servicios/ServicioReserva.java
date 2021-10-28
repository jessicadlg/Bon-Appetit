package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosReserva;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;

import java.text.ParseException;
import java.util.List;

public interface ServicioReserva {
    void confirmarReserva(DatosReserva reserva) throws ReservaException, ParseException;

    List<String> consultarDisponibilidad(String fecha, String hora, Integer comensales) throws ParseException;
}
