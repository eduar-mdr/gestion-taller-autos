package controlador;

import dao.EmpleadoDao;
import dao.ReporteServicioDao;
import modelo.Empleado;
import modelo.ReporteServicio;

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
import java.sql.SQLException;

@WebServlet("/reporte-servicio")
public class ReporteServicioControlador extends HttpServlet {

    private final ReporteServicioDao reporteDao = new ReporteServicioDao();
    private final EmpleadoDao empleadoDao = new EmpleadoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            cargarMecanicos(request);
            request.getRequestDispatcher("vistas/servicios/reporte.jsp").forward(request, response);
        } else if ("generar".equals(accion)) {
            generarReporte(request, response);
        } else if ("pdf".equals(accion)) {
            generarPDF(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // 🔹 Llena el combo de mecánicos
    private void cargarMecanicos(HttpServletRequest request) throws ServletException {
        try {
            List<Empleado> mecanicos = empleadoDao.listar();
            request.setAttribute("mecanicos", mecanicos);
        } catch (SQLException e) {
            throw new ServletException("Error al cargar mecánicos", e);
        }
    }

    // 🔹 Generar reporte para la vista JSP
    private void generarReporte(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo"); // "fecha" o "mecanico"
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        String idMecanicoStr = request.getParameter("idMecanico");

        if (fechaInicio == null || fechaInicio.isEmpty()) fechaInicio = "2000-01-01";
        if (fechaFin == null || fechaFin.isEmpty())       fechaFin   = "2099-12-31";

        List<ReporteServicio> reportes;
        BigDecimal totalGeneral = BigDecimal.ZERO;

        if ("mecanico".equals(modo) && idMecanicoStr != null && !idMecanicoStr.isEmpty()) {
            int idMecanico = Integer.parseInt(idMecanicoStr);
            reportes = reporteDao.obtenerServiciosPorMecanico(idMecanico, fechaInicio, fechaFin);
            request.setAttribute("idMecanicoSeleccionado", idMecanico);
        } else {
            modo = "fecha";
            reportes = reporteDao.obtenerServiciosPorFecha(fechaInicio, fechaFin);
        }

        for (ReporteServicio r : reportes) {
            if (r.getIngreso() != null) {
                totalGeneral = totalGeneral.add(r.getIngreso());
            }
        }

        cargarMecanicos(request);

        request.setAttribute("modo", modo);
        request.setAttribute("reportes", reportes);
        request.setAttribute("totalGeneral", totalGeneral);
        request.setAttribute("fechaInicio", fechaInicio);
        request.setAttribute("fechaFin", fechaFin);

        request.getRequestDispatcher("vistas/servicios/reporte.jsp").forward(request, response);
    }

    // 🔹 Generar PDF igual que el de ingresos, pero con columnas de servicios
    private void generarPDF(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        String idMecanicoStr = request.getParameter("idMecanico");

        if (fechaInicio == null || fechaInicio.isEmpty()) fechaInicio = "2000-01-01";
        if (fechaFin == null || fechaFin.isEmpty())       fechaFin   = "2099-12-31";

        List<ReporteServicio> reportes;
        BigDecimal totalGeneral = BigDecimal.ZERO;

        if ("mecanico".equals(modo) && idMecanicoStr != null && !idMecanicoStr.isEmpty()) {
            int idMecanico = Integer.parseInt(idMecanicoStr);
            reportes = reporteDao.obtenerServiciosPorMecanico(idMecanico, fechaInicio, fechaFin);
        } else {
            modo = "fecha";
            reportes = reporteDao.obtenerServiciosPorFecha(fechaInicio, fechaFin);
        }

        for (ReporteServicio r : reportes) {
            if (r.getIngreso() != null) {
                totalGeneral = totalGeneral.add(r.getIngreso());
            }
        }

        try {
            response.setContentType("application/pdf");
            String filename = "Reporte_Servicios_" +
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + filename + "\"");

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont  = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            Font boldFont   = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

            Paragraph title = new Paragraph("REPORTE DE SERVICIOS REALIZADOS", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setSpacingAfter(20);

            PdfPCell cell = new PdfPCell(new Phrase("Modo:", boldFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);

            String modoTexto = "Por fecha";
            if ("mecanico".equals(modo)) modoTexto = "Por mecánico";
            cell = new PdfPCell(new Phrase(modoTexto, normalFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Período:", boldFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);

            cell = new PdfPCell(new Phrase(fechaInicio + " al " + fechaFin, normalFont));
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

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1.6f, 2.5f, 3f, 1.4f, 1.6f});

            BaseColor headerColor = new BaseColor(0, 123, 255);
            String[] headers = {"Fecha", "Mecánico", "Servicio", "Cantidad", "Ingreso"};

            for (String h : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(h, headerFont));
                headerCell.setBackgroundColor(headerColor);
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                headerCell.setPadding(8);
                table.addCell(headerCell);
            }

            boolean alternate = false;
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            for (ReporteServicio item : reportes) {
                BaseColor rowColor = alternate ? BaseColor.WHITE : new BaseColor(245, 245, 245);
                alternate = !alternate;

                PdfPCell dataCell;

                String fechaStr = item.getFecha() != null ? df.format(item.getFecha()) : "-";
                dataCell = new PdfPCell(new Phrase(fechaStr, normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setPadding(5);
                table.addCell(dataCell);

                dataCell = new PdfPCell(new Phrase(item.getMecanico(), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setPadding(5);
                table.addCell(dataCell);

                dataCell = new PdfPCell(new Phrase(item.getServicio(), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setPadding(5);
                table.addCell(dataCell);

                dataCell = new PdfPCell(new Phrase(String.valueOf(item.getCantidad()), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                dataCell.setPadding(5);
                table.addCell(dataCell);

                dataCell = new PdfPCell(new Phrase(
                        "$" + String.format("%.2f", item.getIngreso()), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                dataCell.setPadding(5);
                table.addCell(dataCell);
            }

            PdfPCell totalCell = new PdfPCell(new Phrase("TOTAL GENERAL", boldFont));
            totalCell.setColspan(4);
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalCell.setPadding(8);
            totalCell.setBackgroundColor(new BaseColor(233, 236, 239));
            table.addCell(totalCell);

            totalCell = new PdfPCell(new Phrase(
                    "$" + String.format("%.2f", totalGeneral), boldFont));
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalCell.setPadding(8);
            totalCell.setBackgroundColor(new BaseColor(233, 236, 239));
            table.addCell(totalCell);

            document.add(table);

            Paragraph footer = new Paragraph(
                    "\nTotal de registros: " + reportes.size(),
                    new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.GRAY));
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingBefore(20);
            document.add(footer);

            document.close();
        } catch (DocumentException e) {
            throw new IOException("Error al generar el PDF: " + e.getMessage());
        }
    }
}

