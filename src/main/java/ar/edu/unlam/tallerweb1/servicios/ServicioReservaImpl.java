package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServicioReservaImpl implements ServicioReserva{

    private RepositorioReserva respositorioReserva;

    @Autowired
    public ServicioReservaImpl(RepositorioReserva respositorioReserva) {
        this.respositorioReserva = respositorioReserva;
    }

    @Override
    public void confirmarReserva(Reserva reserva) throws ReservaException {
        Long idReserva = respositorioReserva.guardarReserva(reserva);
        if(idReserva == null)
            throw new ReservaException();
    }
}
