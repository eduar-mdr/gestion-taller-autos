/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author fuent
 */
public class ServicioTop {

    private String servicio;
    private double total;

    public ServicioTop(String servicio, double total) {
        this.servicio = servicio;
        this.total = total;
    }

    public String getServicio() {
        return servicio;
    }

    public double getTotal() {
        return total;
    }

}