/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Eduar Medrano
 */
public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String documento;
    private String tipoDocumento;
    private String direccion;
    private String telefono;
    private String email;

    //Constructor vacio para inicializar vacio
    public Cliente() {
    }

    //Constructor con parametros
    public Cliente(int idCliente, String nombre, String apellido, String documento, String tipoDocumento, String direccion, String telefono, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
    //Getter y setter

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
