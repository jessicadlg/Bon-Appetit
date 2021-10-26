package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Excepciones.CantidadComensalesInvalida;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaNoDisponible;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ModelAndView confirmarReserva(@ModelAttribute("reserva") Reserva reserva) {
        ModelMap modelMap = new ModelMap();
        try {
            servicioReserva.confirmarReserva(reserva);
            modelMap.put("mnsj","La Reserva se ha realizado con Exito!");
        } catch (ReservaException e) {
            modelMap.put("mnsj","No se ha podido realizar la Reserva.");
        }
        return new ModelAndView("reserva", modelMap);
    }

    @RequestMapping("consultarDisponibilidad")
    public ModelAndView consultarDisponibilidad(@RequestParam String fecha, @RequestParam String hora ,@RequestParam Integer comensales) {
        ModelMap model = new ModelMap();
        try{
            List<String> horariosDisponibles = servicioReserva.consultarDisponibilidad(fecha, hora ,comensales);
            if(horariosDisponibles.contains(hora)){
                model.put("mnsj", "Existe disponibilidad para la Fecha y Hora Especificada. Complete los datos y confirme la Reserva.");
                model.put("horariosDisponibles",horariosDisponibles);
            }else{
                model.put("mnsjError", "No hay disponibilidad para la Fecha y Hora Especificada.");
                model.put("horariosDisponibles", horariosDisponibles);
            }
        }catch (ParseException e){
            model.put("fechaInvalida", "Se ha igresado una Fecha Invalida");
        }catch (CantidadComensalesInvalida e){
            model.put("mnsjError", "La Cantidad de comensales debe ser mayor a cero.");
        }
        return new ModelAndView("reservaMesa", model);
    }
}
