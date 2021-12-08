package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosCambioClave;
import ar.edu.unlam.tallerweb1.AttributeModel.DatosLogin;
import ar.edu.unlam.tallerweb1.AttributeModel.DatosRegistro;
import ar.edu.unlam.tallerweb1.Excepciones.ClaveNuevaIgualActual;
import ar.edu.unlam.tallerweb1.Excepciones.ClavesNoCoinciden;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.Excepciones.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class ControladorRegistrarme {

    private final String REDIRECT_LOGIN_VIEW = "redirect:/login";
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorRegistrarme(ServicioUsuario servicioUsuario){
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(path = "/ir-a-registro", method = RequestMethod.GET)
    public ModelAndView irAlFormularioRegistro(){
        ModelMap model = new ModelMap();
        model.put("datosRegistro", new DatosRegistro());
        return new ModelAndView("registro-usuario", model);
    }

    @RequestMapping(path = "/registrarme", method = RequestMethod.POST)
    public ModelAndView registrar(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {
        ModelMap model = new ModelMap();
        HashMap<String, String> validaciones = validarFormularioRegistro(datosRegistro);
        if (validaciones.size() >= 1) {
            model.put("validaciones", validaciones);
            return new ModelAndView("registro-usuario", model);
        }
        try {
            servicioUsuario.registrar(datosRegistro);
        } catch (UsuarioExistente e) {
            return registroFallido(model, "Este usuario ya existe");
        } catch (ClavesNoCoinciden e) {
            return registroFallido(model, "Las claves deben ser iguales");
        }
        return registroExitoso(model);
    }
    private HashMap<String, String> validarFormularioRegistro(DatosRegistro datosRegistro) {
        HashMap<String, String> validaciones = new HashMap<>();
        if (datosRegistro.getEmail() == null || datosRegistro.getEmail().trim().equals("")) {
            validaciones.put("emailIncompleto", "Debe agregar un email de usuario!");
        }
        if (datosRegistro.getPassword() == null || datosRegistro.getPassword().trim().equals("")) {
            validaciones.put("passwordIncompleto", "Debe agregar una contraseña!");
        }
        return validaciones;
    }
    private ModelAndView registroExitoso(ModelMap model){
        model.put("registrado", true);
        return new ModelAndView(REDIRECT_LOGIN_VIEW, model);
    }

    private ModelAndView registroFallido(ModelMap model, String causa){
        model.put("registrado", false);
        model.put("mostrar", "Nuevo Usuario");
        model.put("registro", new DatosRegistro());
        model.put("error", causa);
        return new ModelAndView("registro-usuario", model);
    }

    @RequestMapping(path = "/ir-a-cambio-clave", method = RequestMethod.GET)
    public ModelAndView irACambioClave(){
        ModelMap model = new ModelMap();
        model.put("datos", new DatosCambioClave());
        return new ModelAndView("cambio-clave", model);
    }

    @RequestMapping(path = "/cambiar-clave", method = RequestMethod.POST)
    public ModelAndView cambiarClave(@ModelAttribute("datos") DatosCambioClave datos) {
        ModelMap model = new ModelMap();
        HashMap<String, String> validacionesClave = validarFormularioClave(datos);
        if (validacionesClave.size() >= 1) {
            model.put("validaciones", validacionesClave);
            return new ModelAndView("cambio-clave", model);
        }
        String error;
        try{
            servicioUsuario.cambiarClave(datos.getEmail(), datos.getClaveNueva(),
                    datos.getRepiteClaveNueva(), datos.getClaveActual());

            return new ModelAndView(REDIRECT_LOGIN_VIEW);
        } catch (ClaveNuevaIgualActual e){
            error = "La clave nueva no puede ser igual a la actual";
        } catch (ClavesNoCoinciden e){
            error = "Las claves no coinciden";
        }
        model.put("error", error);
        return new ModelAndView("cambio-clave", model);
    }

    private HashMap<String, String> validarFormularioClave( DatosCambioClave datos) {
        HashMap<String, String> validacionesClave = new HashMap<>();
        if (datos.getEmail() == null || datos.getEmail().trim().equals("")) {
            validacionesClave.put("emailIncompleto", "Debe agregar un email de usuario!");
        }
        if (datos.getClaveActual() == null || datos.getClaveActual() .trim().equals("")) {
            validacionesClave.put("passwordActualIncompleto", "Debe agregar su contraseña!");
        }
        if (datos.getClaveNueva() == null || datos.getClaveNueva() .trim().equals("")) {
            validacionesClave.put("passwordNuevaIncompleto", "Debe agregar una nueva contraseña!");
        }
        if (datos.getRepiteClaveNueva() == null || datos.getRepiteClaveNueva() .trim().equals("")) {
            validacionesClave.put("passwordRepiteIncompleto", "Debe reingresar la contraseña!");
        }
        return validacionesClave;
    }

}