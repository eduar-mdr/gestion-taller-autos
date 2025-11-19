/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author MINEDUCYT
 */
import dao.RepuestoDao;
import modelo.Repuesto;
import java.sql.SQLException;
import java.util.List;

public class RepuestoServicio {
    


  private RepuestoDao repuestoDao = new RepuestoDao();

    // Index
    public List<Repuesto> obtenerRepuestos() throws SQLException {
        return repuestoDao.listar();
    }

    // Store
    public void registrarRepuesto(Repuesto r) throws SQLException {

        if (r.getNombre() == null || r.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del repuesto es obligatorio");
        }
        if (r.getDescripcion() == null || r.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (r.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        }

        repuestoDao.insertar(r);
    }

    // Show
    public Repuesto obtenerPorId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID del repuesto no es válido");
        }
        return repuestoDao.buscarPorId(id);
    }

    // Update
    public void actualizar(Repuesto r) {

        if (r.getNombre() == null || r.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del repuesto es obligatorio");
        }
        if (r.getDescripcion() == null || r.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (r.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        }

        repuestoDao.actualizar(r);
    }

    // Delete
    public void eliminar(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID del repuesto no es válido");
        }
        repuestoDao.eliminar(id);
    }
}


