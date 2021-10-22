package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaNoDisponible;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorReserva {

    private ServicioReserva servicioReserva;

    @Autowired
    public ControladorReserva(ServicioReserva servicioReserva) {
        this.servicioReserva = servicioReserva;
    }

    @RequestMapping("reservar")
    public ModelAndView reservar(){
        ModelMap modelMap = new ModelMap();
        modelMap.put("reserva", new Reserva());
        return new ModelAndView("reservaMesa", modelMap);
    }

    @RequestMapping("/confirmarReserva")
    public ModelAndView confirmarReserva() {
        ModelMap modelMap = new ModelMap();
        try {
            Reserva reserva = new Reserva();
            reserva.setFecha(new Date());
            servicioReserva.confirmarReserva(reserva);
            modelMap.put("mnsj","La Reserva se ha realizado con Exito!");
        } catch (ReservaException e) {
            modelMap.put("mnsj","No se ha podido realizar la Reserva.");
        }
        return new ModelAndView("reserva", modelMap);
    }

    @RequestMapping("consultarDisponibilidad")
    public ModelAndView consultarDisponibilidad(@RequestParam String fecha, @RequestParam Integer comensales) {
        ModelMap model = new ModelMap();
        try{
            List<Reserva> horariosDisponibles = servicioReserva.consultarDisponibilidad(fecha, comensales);
            model.put("horariosDisponibles", horariosDisponibles);
        }catch (ReservaNoDisponible e){
            model.put("reservaNoDisponible", "No hay disponibilidad para la Fecha Especificada.");
        }catch (ParseException e){
            model.put("fechaInvalida", "Se ha igresado una Fecha Invalida");
        }
        return new ModelAndView("reservarMesa", model);
    }
}
