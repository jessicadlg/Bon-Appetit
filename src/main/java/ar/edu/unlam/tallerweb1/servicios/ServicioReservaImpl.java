package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.CantidadComensalesInvalida;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ServicioReservaImpl implements ServicioReserva {

    private static final Integer COMENSALES_POR_MESA = 4;
    private RepositorioReserva respositorioReserva;
    private List<String> HORARIOS;
    private Integer MESAS_TOTALES;

    @Autowired
    public ServicioReservaImpl(RepositorioReserva respositorioReserva) {
        this.respositorioReserva = respositorioReserva;
        this.HORARIOS = Arrays.asList("12:00","14:00","16:00","18:00","20:00","22:00");
        this.MESAS_TOTALES = 30;
    }

    @Override
    public void confirmarReserva(Reserva reserva) throws ReservaException {
        Long idReserva = respositorioReserva.guardarReserva(reserva);
        if (idReserva == null)
            throw new ReservaException();
    }

    @Override
    public List<String> consultarDisponibilidad(String fecha, String hora, Integer comensales) throws ParseException {
        if(comensales == 0){
            throw new CantidadComensalesInvalida();
        }
        Long cantidadMesasReservadas = respositorioReserva.obtenerMesasReservadasPor(this.pasarFechaDeStringADate(fecha), hora);
        List<String> horariosDisponibles = new ArrayList<String>();
        if (cantidadMesasReservadas == null){
            horariosDisponibles = this.HORARIOS;
        }else{
            Integer mesasAReservar = this.calcularCantidadMesas(comensales);
            Long mesasDisponibles = this.obtenerMesasDisponibles(cantidadMesasReservadas);
            if(verificarMesasDisponibles(mesasAReservar, mesasDisponibles)){
                horariosDisponibles = obtenerHorariosDisponibles(hora);
            }
        }
        return horariosDisponibles;
    }

    private List<String> obtenerHorariosDisponibles(String hora) {
        List<String> horariosDisponibles = this.HORARIOS;
        if(horariosDisponibles.contains(hora)){
            horariosDisponibles.remove(hora);
        }
        return horariosDisponibles;
    }

    private Boolean verificarMesasDisponibles(Integer mesasAReservar, Long mesasDisponibles) {
        return mesasAReservar > mesasDisponibles;
    }

    private Long obtenerMesasDisponibles(Long mesasReservadas) {
        return this.MESAS_TOTALES - mesasReservadas;
    }

    public Integer calcularCantidadMesas(Integer comensales) {
        return (int) Math.ceil(comensales/COMENSALES_POR_MESA);
    }

    private Date pasarFechaDeStringADate(String fecha) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(fecha);
    }
}
