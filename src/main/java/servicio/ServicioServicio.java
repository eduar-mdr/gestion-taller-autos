/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author Eduar Medrano
 */
import dao.ServicioDao;
import modelo.Servicio;

import java.sql.SQLException;
import java.util.List;


public class ServicioServicio {
    private ServicioDao servicioDao = new ServicioDao();

    //Index
    public List<Servicio> obtenerServicios() throws SQLException {
        return servicioDao.listar();
    }
    
    //Store
    public void registrarServicio(Servicio m) throws SQLException {
        // Validaciones antes de guardar
        if (m.getNombre() == null || m.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (m.getDescripcion() == null || m.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (m.getPrecio() == null || m.getPrecio().isNaN()) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }

        //Guardamos
        servicioDao.insertar(m);
    }
    
    //Show
    public Servicio obtenerPorId(int id) {
        // Validaciones antes de buscar
        if (id < 1) {
            throw new IllegalArgumentException("El Identificador no es válido");
        }
        return servicioDao.buscarPorId(id);
    }
    
    //Update
    public void actualizar(Servicio m) {
        System.out.println(m);
        //Validaciones
        if (m.getNombre() == null || m.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (m.getDescripcion() == null || m.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (m.getPrecio() == null || m.getPrecio().isNaN()) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }
        servicioDao.actualizar(m);
    }
    
    //Delete
    public void eliminar(int id) {
        //Agregar validaciones antes de borrrar
        servicioDao.eliminar(id);
    }
}
