package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;

public interface ServicioReserva {
    void confirmarReserva(Reserva reserva) throws ReservaException;
}
