package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;

    @OneToMany
    List<Producto> listaProductos;

    public List<Producto> getListaProductos() {
    if(listaProductos == null)
        listaProductos=new ArrayList<>();
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Double getTotal() {
    if (total== null)
        total= 0.0;
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
