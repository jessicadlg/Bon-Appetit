package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Reserva;

public interface RepositorioReserva {

    Long guardarReserva(Reserva reserva);

    Reserva buscarPorId(Long id);
}
