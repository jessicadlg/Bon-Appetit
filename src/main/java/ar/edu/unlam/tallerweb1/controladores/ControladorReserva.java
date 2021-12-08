package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosReserva;
import ar.edu.unlam.tallerweb1.Excepciones.CantidadComensalesInvalida;
import ar.edu.unlam.tallerweb1.Excepciones.FechaInvalida;
import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorReserva {

    private ServicioReserva servicioReserva;

    @Autowired
    public ControladorReserva(ServicioReserva servicioReserva) {
        this.servicioReserva = servicioReserva;
    }

    @RequestMapping("reservar")
    public ModelAndView reservarFormulario() {
        return procesarReservar(null);
    }

    @RequestMapping("reserva-error")
    public ModelAndView reservar() {
        return procesarReservar("No se ha podido realizar reserva");
    }

    @RequestMapping("reserva-exitosa")
    public ModelAndView reservaExitosa() {
        return procesarReservar("La Reserva se ha realizado con Exito!");
    }

    @RequestMapping(value = "/confirmarReserva", method = RequestMethod.POST)
    public ModelAndView confirmarReserva(@ModelAttribute("datosReserva") DatosReserva datosReserva) {
        ModelMap modelMap = new ModelMap();
        HashMap<String, String> validaciones = validarFormulario(datosReserva);
        if (validaciones.size() >= 1) {
            modelMap.put("validaciones", validaciones);
            modelMap.put("horaElegida", datosReserva.getHora());
            modelMap.put("comensalesElegidos", datosReserva.getCantidadComensales());
            modelMap.put("fechaConsulta", datosReserva.getFecha());
            modelMap.put("celularPuesto", datosReserva.getCelular());
            modelMap.put("nombrePuesto", datosReserva.getNombre());
            return new ModelAndView("reservaMesa", modelMap);
        }
        try {
            servicioReserva.confirmarReserva(datosReserva);
            modelMap.put("reservaConfirmada", "La Reserva se ha realizado con Exito!");
            return new ModelAndView("redirect:reserva-exitosa");

        } catch (ReservaException | ParseException e) {
            modelMap.put("errorReserva", "No se ha podido realizar la Reserva.");
        }
        return new ModelAndView("redirect:reserva-error");
    }

    @RequestMapping("consultarDisponibilidad")
    public ModelAndView consultarDisponibilidad(@RequestParam(defaultValue = "") String fecha, @RequestParam(defaultValue = "") String hora, @RequestParam(defaultValue = "0") Integer cantidadComensales) {
        ModelMap model = new ModelMap();

        HashMap<String, String> validaciones = validarFormularioConsulta(fecha, hora);

        model.put("fechaConsulta", fecha);
        model.put("horaElegida", hora);
        model.put("comensalesElegidos", cantidadComensales);
        if (validaciones.size() >= 1) {
            model.put("validacionesConsulta", validaciones);
            return new ModelAndView("reservaMesa", model);
        }

        try {
            List<String> horariosDisponibles = servicioReserva.consultarDisponibilidad(fecha, hora, cantidadComensales);
            if (horariosDisponibles.contains(hora)) {
                model.put("reservaDisponible", "Existe disponibilidad para la Fecha y Hora Especificada. Complete los datos y confirme la Reserva.");
                model.put("horariosDisponibles", horariosDisponibles);
            } else {
                model.put("reservaNoDisponible", "No hay disponibilidad para la Fecha y Hora Especificada. Por favor consulte en otro horario");
                model.put("horariosDisponibles", horariosDisponibles);
            }
        } catch (ParseException e) {
            model.put("fechaInvalida", "Se ha igresado una Fecha Invalida");
        } catch (CantidadComensalesInvalida e) {
            model.put("mnsjCantidadComensalesInvalida", "La Cantidad de comensales debe ser mayor a cero.");
        } catch (FechaInvalida e) {
            model.put("fechaPasada", "No se puede elegir una fecha pasada");
        }
        return new ModelAndView("reservaMesa", model);
    }


    private HashMap<String, String> validarFormulario(DatosReserva datosReserva) {
        HashMap<String, String> validaciones = new HashMap<>();
        if (datosReserva.getNombre() == null || datosReserva.getNombre().trim().equals("")) {
            validaciones.put("nombreIncompleto", "Debe agregar un nombre");
        }
        if (datosReserva.getNombre() == null || datosReserva.getCelular().trim().equals("")) {
            validaciones.put("celularIncompleto", "Debe agregar un número de telefono");
        }
        if (datosReserva.getNombre() == null || datosReserva.getFecha().trim().equals("")) {
            validaciones.put("fechaIncompleta", "Debe agregar una fecha");
        }
        if (datosReserva.getNombre() == null || datosReserva.getHora().trim().equals("")) {
            validaciones.put("horaIncompleta", "Debe agregar un horario");
        }
        if (datosReserva.getNombre() == null || datosReserva.getCantidadComensales() < 1) {
            validaciones.put("cantidadIncompleta", "Debe agregar un número valido mayor a 0");
        }
        return validaciones;
    }

    private HashMap<String, String> validarFormularioConsulta(String fecha, String hora) {
        HashMap<String, String> validacionesConsulta = new HashMap<>();
        if (fecha.trim().equals("") || fecha == null) {
            validacionesConsulta.put("fechaConsultaVacia", "Debe ingresar una fecha para poder consultar");
        }
        if (hora.trim().equals("") || hora == null) {
            validacionesConsulta.put("horaConsultaVacia", "Debe ingresar una hora para poder consultar");
        }
        return validacionesConsulta;
    }

    private ModelAndView procesarReservar(String mensajeReserva) {
        ModelMap modelMap = new ModelMap();
        if (mensajeReserva != null) {
            modelMap.put("mensajeReserva", mensajeReserva);
        }
        modelMap.put("datosReserva", new DatosReserva());
        return new ModelAndView("reservaMesa", modelMap);
    }

}
