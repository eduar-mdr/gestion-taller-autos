package controlador;

import dao.ReporteRepuestoDao;
import dao.ProveedorDao;      // asumiendo que ya lo tienes
import modelo.ReporteRepuesto;
import modelo.Proveedor;     // idem arriba

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/reporte-repuesto")
public class ReporteRepuestoControlador extends HttpServlet {

    private ReporteRepuestoDao reporteDAO = new ReporteRepuestoDao();
    private ProveedorDao proveedorDao = new ProveedorDao(); // para llenar combo de proveedores

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            // Mostrar solo formulario de filtros
            List<Proveedor> proveedores = proveedorDao.listar();
            request.setAttribute("proveedores", proveedores);

            request.getRequestDispatcher("/vistas/repuestos/repuestos-uso.jsp")
                   .forward(request, response);

        } else if (accion.equals("generar")) {
            generarReporte(request, response);
        } else if (accion.equals("pdf")) {
            generarPDF(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void generarReporte(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin    = request.getParameter("fechaFin");
        String idProvParam = request.getParameter("idProveedor");

        if (fechaInicio == null || fechaInicio.isEmpty()) {
            fechaInicio = "2000-01-01";
        }
        if (fechaFin == null || fechaFin.isEmpty()) {
            fechaFin = "2099-12-31";
        }

        List<ReporteRepuesto> reportes;
        BigDecimal totalConsumo = BigDecimal.ZERO;
        int totalCantidad = 0;

        if (idProvParam != null && !idProvParam.isEmpty()) {
            int idProveedor = Integer.parseInt(idProvParam);
            reportes = reporteDAO.obtenerRepuestosPorProveedor(idProveedor, fechaInicio, fechaFin);
            request.setAttribute("idProveedorSeleccionado", idProveedor);
        } else {
            reportes = reporteDAO.obtenerRepuestosMasUsados(fechaInicio, fechaFin);
        }

        for (ReporteRepuesto r : reportes) {
            if (r.getConsumoTotal() != null) {
                totalConsumo = totalConsumo.add(r.getConsumoTotal());
            }
            totalCantidad += r.getCantidadUsada();
        }

        // Cargar proveedores para el combo
        List<Proveedor> proveedores = proveedorDao.listar();

        request.setAttribute("proveedores", proveedores);
        request.setAttribute("reportes", reportes);
        request.setAttribute("totalConsumo", totalConsumo);
        request.setAttribute("totalCantidad", totalCantidad);
        request.setAttribute("fechaInicio", fechaInicio);
        request.setAttribute("fechaFin", fechaFin);

        request.getRequestDispatcher("/vistas/repuestos/repuestos-uso.jsp")
               .forward(request, response);
    }

    private void generarPDF(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin    = request.getParameter("fechaFin");
        String idProvParam = request.getParameter("idProveedor");

        if (fechaInicio == null || fechaInicio.isEmpty()) {
            fechaInicio = "2000-01-01";
        }
        if (fechaFin == null || fechaFin.isEmpty()) {
            fechaFin = "2099-12-31";
        }

        List<ReporteRepuesto> reportes;
        BigDecimal totalConsumo = BigDecimal.ZERO;
        int totalCantidad = 0;
        String filtroProveedorTexto = "Todos";

        if (idProvParam != null && !idProvParam.isEmpty()) {
            int idProveedor = Integer.parseInt(idProvParam);
            reportes = reporteDAO.obtenerRepuestosPorProveedor(idProveedor, fechaInicio, fechaFin);

            Proveedor p = proveedorDao.buscarPorId(idProveedor);
            if (p != null) {
                filtroProveedorTexto = p.getNombre();
            }
        } else {
            reportes = reporteDAO.obtenerRepuestosMasUsados(fechaInicio, fechaFin);
        }

        for (ReporteRepuesto r : reportes) {
            if (r.getConsumoTotal() != null) {
                totalConsumo = totalConsumo.add(r.getConsumoTotal());
            }
            totalCantidad += r.getCantidadUsada();
        }

        try {
            response.setContentType("application/pdf");
            String filename = "Reporte_Repuestos_Mas_Usados_" +
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

            Document document = new Document(PageSize.A4.rotate()); // horizontal para más columnas
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont  = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            Font boldFont   = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

            Paragraph title = new Paragraph("REPORTE DE REPUESTOS MÁS UTILIZADOS", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setSpacingAfter(20);

            PdfPCell cell = new PdfPCell(new Phrase("Período:", boldFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);

            cell = new PdfPCell(new Phrase(fechaInicio + " al " + fechaFin, normalFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Proveedor:", boldFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);

            cell = new PdfPCell(new Phrase(filtroProveedorTexto, normalFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha de generación:", boldFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);

            cell = new PdfPCell(new Phrase(
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()), normalFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);

            document.add(infoTable);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3f, 3f, 1.5f, 2f});

            BaseColor headerColor = new BaseColor(0, 123, 255);
            String[] headers = {"Repuesto", "Proveedor", "Cantidad usada", "Consumo total"};

            for (String h : headers) {
                PdfPCell hc = new PdfPCell(new Phrase(h, headerFont));
                hc.setBackgroundColor(headerColor);
                hc.setHorizontalAlignment(Element.ALIGN_CENTER);
                hc.setVerticalAlignment(Element.ALIGN_MIDDLE);
                hc.setPadding(8);
                table.addCell(hc);
            }

            boolean alternate = false;
            for (ReporteRepuesto item : reportes) {
                BaseColor rowColor = alternate ? BaseColor.WHITE : new BaseColor(245, 245, 245);
                alternate = !alternate;

                PdfPCell dataCell;

                dataCell = new PdfPCell(new Phrase(item.getRepuesto(), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setPadding(5);
                table.addCell(dataCell);

                dataCell = new PdfPCell(new Phrase(item.getProveedor(), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setPadding(5);
                table.addCell(dataCell);

                dataCell = new PdfPCell(new Phrase(String.valueOf(item.getCantidadUsada()), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                dataCell.setPadding(5);
                table.addCell(dataCell);

                dataCell = new PdfPCell(new Phrase(
                        "$" + String.format("%.2f", item.getConsumoTotal()), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                dataCell.setPadding(5);
                table.addCell(dataCell);
            }

            PdfPCell totalCell = new PdfPCell(new Phrase("TOTAL", boldFont));
            totalCell.setColspan(2);
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalCell.setPadding(8);
            totalCell.setBackgroundColor(new BaseColor(233, 236, 239));
            table.addCell(totalCell);

            PdfPCell totalCantCell = new PdfPCell(new Phrase(
                    String.valueOf(totalCantidad), boldFont));
            totalCantCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalCantCell.setPadding(8);
            totalCantCell.setBackgroundColor(new BaseColor(233, 236, 239));
            table.addCell(totalCantCell);

            PdfPCell totalConsumoCell = new PdfPCell(new Phrase(
                    "$" + String.format("%.2f", totalConsumo), boldFont));
            totalConsumoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalConsumoCell.setPadding(8);
            totalConsumoCell.setBackgroundColor(new BaseColor(233, 236, 239));
            table.addCell(totalConsumoCell);

            document.add(table);

            Paragraph footer = new Paragraph(
                    "\nTotal de repuestos en el reporte: " + reportes.size(),
                    new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.GRAY));
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingBefore(20);
            document.add(footer);

            document.close();

        } catch (DocumentException e) {
            throw new IOException("Error al generar el PDF de repuestos: " + e.getMessage());
        }
    }
}
