package ar.edu.unlam.tallerweb1.AttributeModel;

import java.util.Date;

public class DatosReserva {

    private Date fecha;
    private String hora;
    private Integer cantidadComensales;
    private Integer cantidadMesa;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getCantidadComensales() {
        return cantidadComensales;
    }

    public void setCantidadComensales(Integer cantidadComensales) {
        this.cantidadComensales = cantidadComensales;
    }

    public Integer getCantidadMesa() {
        return cantidadMesa;
    }

    public void setCantidadMesa(Integer cantidadMesa) {
        this.cantidadMesa = cantidadMesa;
    }
}
