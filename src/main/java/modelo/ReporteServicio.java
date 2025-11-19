package modelo;

import java.math.BigDecimal;
import java.util.Date;

public class ReporteServicio {

    private Date fecha;
    private String mecanico;
    private String servicio;
    private int    cantidad;
    private BigDecimal ingreso;   // suma de subtotales del servicio

    public ReporteServicio() {
    }

    public ReporteServicio(Date fecha, String mecanico, String servicio,
                           int cantidad, BigDecimal ingreso) {
        this.fecha = fecha;
        this.mecanico = mecanico;
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.ingreso = ingreso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMecanico() {
        return mecanico;
    }

    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getIngreso() {
        return ingreso;
    }

    public void setIngreso(BigDecimal ingreso) {
        this.ingreso = ingreso;
    }
}
