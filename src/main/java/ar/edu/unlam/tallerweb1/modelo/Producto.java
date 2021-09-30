package ar.edu.unlam.tallerweb1.modelo;

<<<<<<< HEAD
import javax.persistence.*;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
=======
import org.aspectj.apache.bcel.generic.ALOAD;

import javax.persistence.*;
>>>>>>> 6abc755fb16e2a04e5c6ef139922627e80c1f4f4

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO")
    private Long id;
    private Integer idProducto;
    private String nombre;
    private double precio;
    private String codigo;
    @JoinColumn(name = "ID_CATEGORIA")
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Categoria categoria;
    private boolean activo;

    private String nombreImagen;

    public Producto() {

    }

<<<<<<< HEAD
    public Producto(Long id, String codigo, double precio){
        this.id = id;
        this.codigo = codigo;
        this.precio = precio;
    }

=======
    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {this.nombreImagen = nombreImagen;}

>>>>>>> 6abc755fb16e2a04e5c6ef139922627e80c1f4f4
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
