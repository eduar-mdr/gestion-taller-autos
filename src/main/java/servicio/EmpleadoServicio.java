/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author MINEDUCYT
 */
import dao.EmpleadoDao;
import modelo.Empleado;
import conexion.ConexionDB;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoServicio {

    private EmpleadoDao empleadoDao = new EmpleadoDao();

    //Index
    public List<Empleado> obtenerEmpleados() throws SQLException {
        return empleadoDao.listar();
    }

    //store
    public void registrarEmpleado(Empleado m) throws SQLException {
        // Validaciones antes de guardar
        if (m.getNombre() == null || m.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (m.getApellido() == null || m.getApellido().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (m.getDui() == null || m.getDui().isEmpty()) {
            throw new IllegalArgumentException("El DUI es obligatorio y debe ser válido");
        }
        if (m.getTelefono() <= 0) {
            throw new IllegalArgumentException("El teléfono es obligatorio y debe ser válido");
        }
        if (m.getDireccion() == null || m.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        if (m.getCargo() == null || m.getCargo().isEmpty()) {
            throw new IllegalArgumentException("El cargo es obligatorio");
        }
        if (m.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("El usuario asociado es obligatorio");
        }
        if (m.getSalario() == null || m.getSalario().doubleValue() <= 0) {
            throw new IllegalArgumentException("El salario es obligatorio y debe ser mayor que cero");
        }
        if (m.getEstado() == null || m.getEstado().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }
        empleadoDao.insertar(m);
    }

    //Show
    public Empleado obtenerPorId(int id) {
        // Validaciones antes de buscar
        if (id < 1) {
            throw new IllegalArgumentException("El Identificador no es válido");
        }
        return empleadoDao.buscarPorId(id);
    }

    //Update
    public void actualizarEmpleado(Empleado m) {
        System.out.println(m);

        // Validaciones
        if (m.getNombre() == null || m.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (m.getApellido() == null || m.getApellido().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (m.getDui() == null || m.getDui().isEmpty()) {
            throw new IllegalArgumentException("El DUI es obligatorio y debe ser válido");
        }
        if (m.getTelefono() <= 0) {
            throw new IllegalArgumentException("El teléfono es obligatorio y debe ser válido");
        }
        if (m.getDireccion() == null || m.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        if (m.getCargo() == null || m.getCargo().isEmpty()) {
            throw new IllegalArgumentException("El cargo es obligatorio");
        }
        if (m.getSalario() == null || m.getSalario().doubleValue() <= 0) {
            throw new IllegalArgumentException("El salario es obligatorio y debe ser mayor que cero");
        }
        if (m.getEstado() == null || m.getEstado().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }

        empleadoDao.actualizar(m);
    }

//Delete
    public void eliminar(int id) {
        //Agregar validaciones antes de borrrar
        empleadoDao.eliminar(id);
    }
}
