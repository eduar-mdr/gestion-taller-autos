/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author fuent
 */
public class ClienteMensual {
    private int mesNumero;
    private String mesNombre;
    private int cantidad;

    public ClienteMensual(int mesNumero, String mesNombre, int cantidad) {
        this.mesNumero = mesNumero;
        this.mesNombre = mesNombre;
        this.cantidad = cantidad;
    }

    public int getMesNumero() {
        return mesNumero;
    }

    public String getMesNombre() {
        return mesNombre;
    }

    public int getCantidad() {
        return cantidad;
    }
    
}