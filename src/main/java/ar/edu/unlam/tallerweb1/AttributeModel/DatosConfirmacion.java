package ar.edu.unlam.tallerweb1.AttributeModel;

public class DatosConfirmacion {

    private String calle;
    private String altura;
    private String localidad;

    private String nombre;
    private String telefono;

    private String total;
    private String tiempoPreparacion;

    private Long idPedido;

    public DatosConfirmacion() {
    }

    public DatosConfirmacion(String calle, String altura, String localidad, String nombre, String telefono, String total, String tiempoPreparacion) {
        this.calle = calle;
        this.altura = altura;
        this.localidad = localidad;
        this.nombre = nombre;
        this.telefono = telefono;
        this.total = total;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(String tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }
}
