/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author fuent
 */
import modelo.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import conexion.ConexionDB;

public class DashboardDao {

    private Connection getConnection() throws SQLException {
        return ConexionDB.getConnection();
    }

    private String nombreMes(int mes) {
        switch (mes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "Mes " + mes;
        }
    }

// Ventas totales por mes 
    public List<VentaMensual> obtenerVentasMensuales() {
        List<VentaMensual> lista = new ArrayList<>();

        try (Connection conn = getConnection(); CallableStatement cs = conn.prepareCall("{CALL sp_VentasMensuales}"); ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                int mes = rs.getInt("mes");
                double total = rs.getDouble("total");
                lista.add(new VentaMensual(mes, nombreMes(mes), total));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

// Top 5 servicios más vendidos
    public List<ServicioTop> obtenerTopServicios() {
        List<ServicioTop> lista = new ArrayList<>();

        try (Connection conn = getConnection(); CallableStatement cs = conn.prepareCall("{CALL sp_TopServicios}"); ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                lista.add(new ServicioTop(
                        rs.getString("servicio"),
                        rs.getDouble("total")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

// Clientes registrados por mes
    public List<ClienteMensual> obtenerClientesMensuales() {
        List<ClienteMensual> lista = new ArrayList<>();

        try (Connection conn = getConnection(); CallableStatement cs = conn.prepareCall("{CALL sp_ClientesPorMes}"); ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                int mes = rs.getInt("mes");
                int cantidad = rs.getInt("cantidad");
                lista.add(new ClienteMensual(mes, nombreMes(mes), cantidad));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
// Obtener los Ingresos de cada categoria

    public List<ServicioTop> obtenerIngresosPorCategoria() {
        List<ServicioTop> lista = new ArrayList<>();

        String sql = "{CALL sp_IngresosPorCategoriaServicio}";

        try (Connection conn = getConnection(); CallableStatement cs = conn.prepareCall(sql); ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                String categoria = rs.getString("categoria");
                double total = rs.getDouble("total");

                lista.add(new ServicioTop(categoria, total));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}