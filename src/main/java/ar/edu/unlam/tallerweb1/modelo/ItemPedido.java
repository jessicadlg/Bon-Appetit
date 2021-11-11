package ar.edu.unlam.tallerweb1.modelo;


import javax.persistence.*;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ITEM_PEDIDO")
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Producto producto;

    private Integer cantidad;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Pedido pedido;


    public ItemPedido(){
        this.cantidad = 0;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
