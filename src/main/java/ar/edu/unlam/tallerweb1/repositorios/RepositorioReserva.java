package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Reserva;

import java.util.Date;
import java.util.List;

public interface RepositorioReserva {

    Long guardarReserva(Reserva reserva);

    Reserva buscarPorId(Long id);

    List<Reserva> buscarMesasPorFecha(Date fecha);
}
