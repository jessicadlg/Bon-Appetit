package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioPedidoImpl implements RepositorioPedido{

    private SessionFactory sessionFactory;
    private RestTemplate restTemplate = new RestTemplate();
    private Ubicacion UBICACION_RESTAURANTE = new Ubicacion(-58.56302836691231,-34.67052234258952);

    @Autowired
    public RepositorioPedidoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Pedido obtenerPedido(Long idPedido) {
        final Session session = this.sessionFactory.getCurrentSession();
        Pedido pedido = (Pedido) session.createCriteria(Pedido.class)
                        .add(Restrictions.eq("id",idPedido)).uniqueResult();

        return pedido;
    }

    @Override
    public void actualizarPedido(Pedido pedido) {
        sessionFactory.getCurrentSession().save(pedido);
    }

    @Override
    public Long generarPedido(Pedido pedido) {
        return (Long) sessionFactory.getCurrentSession().save(pedido);
    }

    @Override
    public ItemPedido obtenerItemPedido(Long idPedido, Long idProducto) {
        final Session session = this.sessionFactory.getCurrentSession();
        ItemPedido itemPedido = (ItemPedido) session.createCriteria(ItemPedido.class)
                .add(Restrictions.eq("producto.id",idProducto))
                .add(Restrictions.eq("pedido.id",idPedido))
                .uniqueResult();

        return itemPedido;
    }

    @Override
    public void guardarItemPedido(ItemPedido itemPedido) {
        sessionFactory.getCurrentSession().save(itemPedido);
    }

    @Override
    public List<ItemPedido> obtenerItemsPedido(Long idPedido) {
        final Session session = this.sessionFactory.getCurrentSession();
        List<ItemPedido>itemPedidos = session.createCriteria(ItemPedido.class)
                .add(Restrictions.eq("pedido.id",idPedido)).list();
        return itemPedidos;
    }

    public void eliminarItemPedido(ItemPedido itemPedido){
        final Session session = this.sessionFactory.getCurrentSession();
        session.delete("ItemPedido",itemPedido);
    }

    @Override
    public Ubicacion obtenerLatitudLongitud(String calle, String altura, String localidad) {
        Direcciones direccion = this.restTemplate.getForObject("https://apis.datos.gob.ar/georef/api/direcciones?direccion=" + calle + " " + altura + "&provincia=06&departamento=06427&localidad=" + localidad + "&max=1&exacto=true", Direcciones.class);
        if(direccion.getDirecciones().size()<1){
            return null;
        }
        return direccion.getDirecciones().get(0).getUbicacion();
    }

    @Override
    public Routes consultarDistanciaDelViaje(Ubicacion ubicacion) {
        Viaje viajes = this.restTemplate.getForObject("http://router.project-osrm.org/route/v1/foot/" + ubicacion.getLon() + "," + ubicacion.getLat() + ";" + this.UBICACION_RESTAURANTE.getLon() + "," + this.UBICACION_RESTAURANTE.getLat(), Viaje.class);
        return viajes.getRoutes().get(0);
    }

    @Override
    public Calles listarCalles() {

        Calles calleRespuesta = restTemplate.getForObject("https://apis.datos.gob.ar/georef/api/calles?departamento=06427&aplanar=true&campos=estandar&max=3000&exacto=true\" -H \"accept: application/json", Calles.class);

        Localidades localidadesRespuesta = restTemplate.getForObject("https://apis.datos.gob.ar/georef/api/localidades?provincia=06&departamento=06427&campos=id,nombre&max=20&exacto=true", Localidades.class);

        calleRespuesta.setLocalidad(localidadesRespuesta);

        return calleRespuesta;
    }

    @Override
    public Localidades obtenerLocalidad(String idLocalidad) {
        Localidades localidadesRespuesta = restTemplate.getForObject("https://apis.datos.gob.ar/georef/api/localidades?id=6427010010&aplanar=true&campos=estandar&max=1&exacto=true", Localidades.class);
        return localidadesRespuesta;
    }

}
