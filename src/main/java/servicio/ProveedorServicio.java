/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import dao.ProveedorDao;
import modelo.Proveedor;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Eduar Medrano
 */
public class ProveedorServicio {
    
    private ProveedorDao proveedorDao = new ProveedorDao();
    
     // Index
    public List<Proveedor> obtenerProveedores() throws SQLException {
        return proveedorDao.listar();
    }

    // Store
    public void registrarProveedor(Proveedor p) throws SQLException {

        if (p.getNombre() == null || p.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es obligatorio");
        }
        if (p.getContacto() == null || p.getContacto().isEmpty()) {
            throw new IllegalArgumentException("El contacto es obligatorio");
        }
        if (p.getTelefono() == null || p.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (p.getEmail() == null || p.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }
        if (p.getDireccion() == null || p.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }

        proveedorDao.insertar(p);
    }

    // Show
    public Proveedor obtenerPorId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID del proveedor no es válido");
        }
        return proveedorDao.buscarPorId(id);
    }

    // Update
    public void actualizar(Proveedor p) {

        if (p.getNombre() == null || p.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es obligatorio");
        }
        if (p.getContacto() == null || p.getContacto().isEmpty()) {
            throw new IllegalArgumentException("El contacto es obligatorio");
        }
        if (p.getTelefono() == null || p.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (p.getEmail() == null || p.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }
        if (p.getDireccion() == null || p.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }

        proveedorDao.actualizar(p);
    }

    // Delete
    public void eliminar(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID del proveedor no es válido");
        }
        proveedorDao.eliminar(id);
    }
    
}
