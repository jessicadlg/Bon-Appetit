package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaNoDisponible;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<Reserva> consultarDisponibilidad(String fecha, Integer comensales) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = formatter.parse(fecha);
        if(respositorioReserva.buscarMesasPorFecha(fechaDate).size() < 1){
            throw new ReservaNoDisponible();
        }
        return respositorioReserva.buscarMesasPorFecha(fechaDate);
    }
}
