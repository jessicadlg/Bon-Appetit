package ar.edu.unlam.tallerweb1.modelo;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private Long id;

    private Double total;

    private Double tiempoPreparacion;

    public Pedido(){
        this.total = 0.0;
        this.tiempoPreparacion= 0.0;
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

    public Double getTotal() {

        return total;
    }

    public Double getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Double tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }
}
