package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Excepciones.ListaCategoriaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ProductoNoEncontrado;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCategoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorProducto {

    private ServicioProducto servicioProductos;
    private ServicioCategoria servicioCategoria;

    @Autowired
    public ControladorProducto(ServicioProducto servicioProducto, ServicioCategoria servicioCategoria) {
        this.servicioProductos = servicioProducto;
        this.servicioCategoria = servicioCategoria;
    }

    @RequestMapping("/listarProductos")
    public ModelAndView listarProductos() throws ListaNoEncontrada {

        ModelMap modelo = new ModelMap();

        try {
            List<Producto> listaProductos = this.servicioProductos.listarProductos();
            List<Categoria> listaCategorias = servicioCategoria.listarCategorias();
            modelo.put("listaCategorias", listaCategorias);
            modelo.put("listaProductos", listaProductos);
        } catch (ListaNoEncontrada e) {
            modelo.put("msgError", "No hay productos");
        }catch (ListaCategoriaNoEncontrada f) {
            modelo.put("categoriasNoEncontradas", "No se encontro ninguna categoria por mostrar");
        }
        return new ModelAndView("productos", modelo);
    }

    @RequestMapping("/productos-activos")
    public ModelAndView listarProductosActivos() {

        ModelMap modelo = new ModelMap();

        try {
            List<Producto> listaProductos = this.servicioProductos.listarProductosActivos();
            modelo.put("listaProductos", listaProductos);
        } catch (ListaNoEncontrada e) {
            modelo.put("msgError", "No hay productos activos para mostrar");
            return new ModelAndView("productos", modelo);
        }
        return new ModelAndView("productos", modelo);
    }

    @RequestMapping("buscar-producto")
    public ModelAndView buscarProductoPorNombre(@RequestParam(value = "nombreProducto") String nombreProducto) {

        ModelMap modelo = new ModelMap();

        try {
            Producto productoBuscado = this.servicioProductos.buscarProductoPorNombre(nombreProducto);
            modelo.put("productoBuscado", productoBuscado);
        } catch (ProductoNoEncontrado e) {
            modelo.put("msgErrorProducto", "No se encontro el producto buscado");
            try {
                List<Producto> listaProductos = this.servicioProductos.listarProductos();
                modelo.put("listaProductos", listaProductos);
            } catch (ListaNoEncontrada f) {
                modelo.put("msgError", "No hay productos");
            }
        }
        return new ModelAndView("productos", modelo);
    }

//    @RequestMapping("listar-categorias")
//    public ModelAndView listarCategorias() {
//        ModelMap modelo = new ModelMap();
//
//        try {
//            List<Categoria> listaCategorias = servicioCategoria.listarCategorias();
//            modelo.put("listaCategorias", listaCategorias);
//        } catch (ListaCategoriaNoEncontrada e) {
//        }
//
//        return new ModelAndView("productos", modelo);
//    }
}
