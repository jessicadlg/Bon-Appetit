package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Excepciones.CantidadComensalesInvalida;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

@Controller
public class ControladorReserva {

    private ServicioReserva servicioReserva;

    @Autowired
    public ControladorReserva(ServicioReserva servicioReserva) {
        this.servicioReserva = servicioReserva;
    }

    @RequestMapping("reservar")
    public ModelAndView reservar(@RequestParam(value = "confirmada",defaultValue = "false") Boolean confirmada){
        ModelMap modelMap = new ModelMap();
        if(confirmada==false){
            modelMap.put("reservaConfirmada","La Reserva se ha realizado con Exito!");
        }
        modelMap.put("reserva", new Reserva());
        return new ModelAndView("reservaMesa", modelMap);
    }

    @RequestMapping(value = "/confirmarReserva", method = RequestMethod.POST)
    public ModelAndView confirmarReserva(@ModelAttribute("reserva") Reserva reserva) {
        ModelMap modelMap = new ModelMap();
        try {
            servicioReserva.confirmarReserva(reserva);
            modelMap.put("reservaConfirmada","La Reserva se ha realizado con Exito!");
        } catch (ReservaException e) {
            modelMap.put("errorReserva","No se ha podido realizar la Reserva.");
        }
        return new ModelAndView("redirect:reservar?confirmada=" +  true);
    }

    @RequestMapping("consultarDisponibilidad")
    public ModelAndView consultarDisponibilidad(@RequestParam String fecha, @RequestParam String hora ,@RequestParam Integer cantidadComensales) {
        ModelMap model = new ModelMap();
        try{
            List<String> horariosDisponibles = servicioReserva.consultarDisponibilidad(fecha, hora ,cantidadComensales);
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
            model.put("mnsjCantidad", "La Cantidad de comensales debe ser mayor a cero.");
        }
        return new ModelAndView("reservaMesa", model);
    }
}
