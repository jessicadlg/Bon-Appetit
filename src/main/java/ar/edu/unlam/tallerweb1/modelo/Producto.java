package ar.edu.unlam.tallerweb1.modelo;


import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "PRODUCTO_TIPO",discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PRODUCTO")
    private Long id;
    private String nombre;
    private double precio;
    @JoinColumn(name = "ID_CATEGORIA")
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Categoria categoria;
    private Integer cantidadMeGusta;
    private String nombreImagen;
    private String descripcion;

    public Producto() {

    }

    public Producto(String nombre, double precio,  Categoria categoria, Integer cantidadMeGusta, String nombreImagen, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidadMeGusta = cantidadMeGusta;
        this.nombreImagen = nombreImagen;
        this.descripcion = descripcion;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getCantidadMeGusta() {
        return cantidadMeGusta;
    }

    public void setCantidadMeGusta(Integer cantidadMeGusta) {
        this.cantidadMeGusta = cantidadMeGusta;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
