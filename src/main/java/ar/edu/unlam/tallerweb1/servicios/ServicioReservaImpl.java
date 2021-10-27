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
    private ArrayList<String> HORARIOS = new ArrayList<>();
    private Integer MESAS_TOTALES;

    @Autowired
    public ServicioReservaImpl(RepositorioReserva respositorioReserva) {
        this.respositorioReserva = respositorioReserva;
        this.HORARIOS.add("12:00");
        this.HORARIOS.add("14:00");
        this.HORARIOS.add("16:00");
        this.HORARIOS.add("18:00");
        this.HORARIOS.add("20:00");
        this.HORARIOS.add("22:00");
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
        if (comensales < 1) {
            throw new CantidadComensalesInvalida();
        }
        List<String> horariosDisponibles = new ArrayList<String>();
        Long cantidadMesasReservadas = respositorioReserva.obtenerMesasReservadasPor(this.pasarFechaDeStringADate(fecha), hora);
        if (cantidadMesasReservadas == null) {
            horariosDisponibles = this.HORARIOS;
        } else {
            Integer mesasAReservar = this.calcularCantidadMesas(comensales);
            Long mesasDisponibles = this.obtenerMesasDisponibles(cantidadMesasReservadas);
            if (verificarMesasDisponibles(mesasAReservar, mesasDisponibles)) {
                horariosDisponibles = obtenerHorariosDisponibles(hora);
            }
        }
        return horariosDisponibles;
    }

    private ArrayList<String> obtenerHorariosDisponibles(String hora) {
        ArrayList<String> horariosDisponibles = this.HORARIOS;
        if (horariosDisponibles.contains(hora)) {
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
        return (int) Math.ceil(comensales / COMENSALES_POR_MESA);
    }

    private Date pasarFechaDeStringADate(String fecha) throws ParseException {
        String[] fechaArray2 = fecha.split("-");
        String dia = fechaArray2[2];
        String mes = fechaArray2[1];
        String año = fechaArray2[0];
        String fechaNueva = año + "/" + mes + "/" + dia;

        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.parse(fechaNueva);
    }
}
