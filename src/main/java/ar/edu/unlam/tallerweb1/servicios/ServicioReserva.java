package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface ServicioReserva {
    void confirmarReserva(Reserva reserva) throws ReservaException;

    List<Reserva> consultarDisponibilidad(String fecha, Integer comensales) throws ParseException;
}
