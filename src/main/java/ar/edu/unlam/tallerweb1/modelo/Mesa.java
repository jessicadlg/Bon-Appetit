package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String estado;

    public Mesa(){

    }

    public Mesa(Long id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
