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
        if (m.getNombre() == null || m.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (m.getDescripcion() == null || m.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (m.getPrecio() == null || m.getPrecio().isNaN()) {
            throw new IllegalArgumentException("El precio es obligatorio");
        }
        if (m.getCategoria() == null || m.getCategoria().isEmpty()) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        if (m.getDuracionEstimada() == null || m.getDuracionEstimada().isNaN()) {
            throw new IllegalArgumentException("La duración estimada es obligatoria");
        }
        if (m.getEstado() == null || m.getEstado().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }

        // Guardamos
        servicioDao.insertar(m);
    }

//Show
    public Servicio obtenerPorId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El Identificador no es válido");
        }
        return servicioDao.buscarPorId(id);
    }

//Update
    public void actualizar(Servicio m) {
        System.out.println(m);

        if (m.getNombre() == null || m.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (m.getDescripcion() == null || m.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (m.getPrecio() == null || m.getPrecio().isNaN()) {
            throw new IllegalArgumentException("El precio es obligatorio");
        }
        if (m.getCategoria() == null || m.getCategoria().isEmpty()) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        if (m.getDuracionEstimada() == null || m.getDuracionEstimada().isNaN()) {
            throw new IllegalArgumentException("La duración estimada es obligatoria");
        }
        if (m.getEstado() == null || m.getEstado().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }

        servicioDao.actualizar(m);
    }

//Delete
    public void eliminar(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El Identificador no es válido");
        }
        servicioDao.eliminar(id);
    }

}
