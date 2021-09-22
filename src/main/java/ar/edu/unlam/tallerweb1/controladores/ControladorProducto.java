package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorProducto {

    private ServicioProducto servicioProductos;

    @Autowired
    public ControladorProducto(ServicioProducto servicioProducto) {
        this.servicioProductos = servicioProducto;
    }

    @RequestMapping("/listarProductos")
    public ModelAndView listarProductos() throws ListaNoEncontrada {

        ModelMap modelo = new ModelMap();

        try {
            List<Producto> listaProductos = this.servicioProductos.listarProductos();
            modelo.put("listaProductos", listaProductos);
        } catch (ListaNoEncontrada e) {
            modelo.put("msgError","No hay productos");
            return new ModelAndView("productos", modelo);
        }
        return new ModelAndView("productos", modelo);
    }

    @RequestMapping("productos-activos")
    public ModelAndView listarProductosActivos() {

        ModelMap modelo = new ModelMap();
        modelo.remove("listaProductos");

        try {
            List<Producto> listaProductos = this.servicioProductos.listarProductosActivos();
            modelo.put("listaProductos", listaProductos);
        } catch (ListaNoEncontrada e) {
            modelo.put("msgError", "No hay productos activos para mostrar");
            return new ModelAndView("productos", modelo);
        }
        return new ModelAndView("productos", modelo);
    }
}
