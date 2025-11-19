package modelo;

import java.math.BigDecimal;

public class ReporteRepuesto {

    private String repuesto;
    private String proveedor;
    private int cantidadUsada;
    private BigDecimal consumoTotal;

    public ReporteRepuesto() {
    }

    public ReporteRepuesto(String repuesto, String proveedor, int cantidadUsada, BigDecimal consumoTotal) {
        this.repuesto = repuesto;
        this.proveedor = proveedor;
        this.cantidadUsada = cantidadUsada;
        this.consumoTotal = consumoTotal;
    }

    public String getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(String repuesto) {
        this.repuesto = repuesto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidadUsada() {
        return cantidadUsada;
    }

    public void setCantidadUsada(int cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }

    public BigDecimal getConsumoTotal() {
        return consumoTotal;
    }

    public void setConsumoTotal(BigDecimal consumoTotal) {
        this.consumoTotal = consumoTotal;
    }
}
