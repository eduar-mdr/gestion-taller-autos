/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author Eduar Medrano
 */

import dao.ClienteDao;
import modelo.Cliente;

import java.sql.SQLException;
import java.util.List;


public class ClienteServicio {
    
    private ClienteDao clienteDao = new ClienteDao();

    //Index
    public List<Cliente> obtenerClientes() throws SQLException {
        return clienteDao.listar();
    }
    
    //Store
    public void registrarCliente(Cliente c) throws SQLException {
        // Validaciones antes de guardar
        if (c.getNombre() == null || c.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (c.getApellido() == null || c.getApellido().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (c.getDocumento() == null || c.getDocumento().isEmpty()) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }
        if (c.getTipoDocumento() == null || c.getTipoDocumento().isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento es obligatorio");
        }
        if (c.getDireccion() == null || c.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        if (c.getTelefono() == null || c.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (c.getEmail() == null || c.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }
        //Guardamos
        clienteDao.insertar(c);
    }
    
    //Show
    public Cliente obtenerPorId(int id) {
        // Validaciones antes de buscar
        if (id < 1) {
            throw new IllegalArgumentException("El Identificador no es válido");
        }
        return clienteDao.buscarPorId(id);
    }

    //Update
    public void actualizar(Cliente c) {
        System.out.println(c);
        //Validaciones
        if (c.getNombre() == null || c.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (c.getApellido() == null || c.getApellido().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (c.getDocumento() == null || c.getDocumento().isEmpty()) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }
        if (c.getTipoDocumento() == null || c.getTipoDocumento().isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento es obligatorio");
        }
        if (c.getDireccion() == null || c.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        if (c.getTelefono() == null || c.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (c.getEmail() == null || c.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }
        clienteDao.actualizar(c);
    }
    
    //Delete
    public void eliminar(int id) {
        //Agregar validaciones antes de borrrar
        clienteDao.eliminar(id);
    }

}