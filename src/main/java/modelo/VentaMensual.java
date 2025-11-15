/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author fuent
 */
public class VentaMensual {
    private int mesNumero;
    private String mesNombre;
    private double total;

    public VentaMensual(int mesNumero, String mesNombre, double total) {
        this.mesNumero = mesNumero;
        this.mesNombre = mesNombre;
        this.total = total;
    }

    public int getMesNumero() {
        return mesNumero;
    }

    public String getMesNombre() {
        return mesNombre;
    }

    public double getTotal() {
        return total;
    }
    
}
