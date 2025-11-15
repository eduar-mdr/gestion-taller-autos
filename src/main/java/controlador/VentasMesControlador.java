/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author fuent
 */

import dao.DashboardDao;
import modelo.VentaMensual;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/ventas-mes")
public class VentasMesControlador extends HttpServlet {

    private DashboardDao dao = new DashboardDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<VentaMensual> lista = dao.obtenerVentasMensuales();

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < lista.size(); i++) {
            VentaMensual v = lista.get(i);
            json.append("{")
                .append("\"mesNombre\":\"").append(v.getMesNombre()).append("\",")
                .append("\"total\":").append(v.getTotal())
                .append("}");
            if (i < lista.size() - 1) json.append(",");
        }
        json.append("]");

        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(json.toString());
    }
}
