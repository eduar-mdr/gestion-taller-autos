/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import dao.DashboardDao;
import java.io.IOException;
import controlador.ServicioTopControlador;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ServicioTop;
/**
 *
 * @author fuent
 */
@WebServlet("/dashboard/ingresos-categoria")
public class IngresosCategoriaControlador extends HttpServlet {

    private DashboardDao dao = new DashboardDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<ServicioTop> lista = dao.obtenerIngresosPorCategoria();

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < lista.size(); i++) {
            ServicioTop s = lista.get(i);
            json.append("{")
                .append("\"categoria\":\"").append(s.getServicio()).append("\",")
                .append("\"total\":").append(s.getTotal())
                .append("}");
            if (i < lista.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(json.toString());
    }
}