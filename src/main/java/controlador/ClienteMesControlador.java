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
import modelo.ClienteMensual;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/clientes-mes")
public class ClienteMesControlador extends HttpServlet {

    private DashboardDao dao = new DashboardDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<ClienteMensual> lista = dao.obtenerClientesMensuales();

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < lista.size(); i++) {
            ClienteMensual c = lista.get(i);
            json.append("{")
                .append("\"mesNombre\":\"").append(c.getMesNombre()).append("\",")
                .append("\"cantidad\":").append(c.getCantidad())
                .append("}");
            if (i < lista.size() - 1) json.append(",");
        }
        json.append("]");

        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(json.toString());
    }
}