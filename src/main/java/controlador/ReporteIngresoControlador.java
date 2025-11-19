/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author fuent
 */
import dao.ReporteIngresoDao;
import modelo.ReporteIngreso;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ingresos")
public class ReporteIngresoControlador extends HttpServlet {
    
    private ReporteIngresoDao reporteDAO = new ReporteIngresoDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion == null || accion.isEmpty()) {
            // Mostrar formulario de filtros
            request.getRequestDispatcher("/vistas/pagos/reporte.jsp").forward(request, response);

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
        
        String tipoReporte = request.getParameter("tipoReporte");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        
        // Si no hay fechas, usar valores por defecto
        if (fechaInicio == null || fechaInicio.isEmpty()) {
            fechaInicio = "2000-01-01";
        }
        if (fechaFin == null || fechaFin.isEmpty()) {
            fechaFin = "2099-12-31";
        }
        
        List<ReporteIngreso> reportes = null;
        BigDecimal totalGeneral = BigDecimal.ZERO;
        
        // Generar reporte según el tipo seleccionado
        switch (tipoReporte) {
            case "servicios":
                reportes = reporteDAO.obtenerIngresosServicios(fechaInicio, fechaFin);
                break;
            case "repuestos":
                reportes = reporteDAO.obtenerIngresosRepuestos(fechaInicio, fechaFin);
                break;
            case "todos":
                reportes = reporteDAO.obtenerIngresosCombinado(fechaInicio, fechaFin);
                break;
            default:
                reportes = reporteDAO.obtenerIngresosCombinado(fechaInicio, fechaFin);
        }
        
        // Calcular total general
        for (ReporteIngreso r : reportes) {
            if (r.getIngresoTotal() != null) {
                totalGeneral = totalGeneral.add(r.getIngresoTotal());
            }
        }
        
        // Enviar datos a la vista
        request.setAttribute("reportes", reportes);
        request.setAttribute("totalGeneral", totalGeneral);
        request.setAttribute("tipoReporte", tipoReporte);
        request.setAttribute("fechaInicio", fechaInicio);
        request.setAttribute("fechaFin", fechaFin);
        
        request.getRequestDispatcher("/vistas/pagos/reporte.jsp").forward(request, response);

    }
    
    private void generarPDF(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tipoReporte = request.getParameter("tipoReporte");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        
        // Si no hay fechas, usar valores por defecto
        if (fechaInicio == null || fechaInicio.isEmpty()) {
            fechaInicio = "2000-01-01";
        }
        if (fechaFin == null || fechaFin.isEmpty()) {
            fechaFin = "2099-12-31";
        }
        
        List<ReporteIngreso> reportes = null;
        BigDecimal totalGeneral = BigDecimal.ZERO;
        
        // Generar reporte según el tipo seleccionado
        switch (tipoReporte) {
            case "servicios":
                reportes = reporteDAO.obtenerIngresosServicios(fechaInicio, fechaFin);
                break;
            case "repuestos":
                reportes = reporteDAO.obtenerIngresosRepuestos(fechaInicio, fechaFin);
                break;
            case "todos":
                reportes = reporteDAO.obtenerIngresosCombinado(fechaInicio, fechaFin);
                break;
            default:
                reportes = reporteDAO.obtenerIngresosCombinado(fechaInicio, fechaFin);
        }
        
        // Calcular total general
        for (ReporteIngreso r : reportes) {
            if (r.getIngresoTotal() != null) {
                totalGeneral = totalGeneral.add(r.getIngresoTotal());
            }
        }
        
        try {
            // Configurar respuesta HTTP
            response.setContentType("application/pdf");
            String filename = "Reporte_Ingresos_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            
            // Crear documento PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            
            // Fuentes
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            
            // Título
            Paragraph title = new Paragraph("REPORTE DE INGRESOS", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);
            
            // Información del reporte
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setSpacingAfter(20);
            
            PdfPCell cell = new PdfPCell(new Phrase("Tipo de Reporte:", boldFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);
            
            String tipoTexto = tipoReporte.equals("servicios") ? "Solo Servicios" : 
                              tipoReporte.equals("repuestos") ? "Solo Repuestos" : "Todos";
            cell = new PdfPCell(new Phrase(tipoTexto, normalFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Período:", boldFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase(fechaInicio + " al " + fechaFin, normalFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Fecha de Generación:", boldFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()), normalFont));
            cell.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(cell);
            
            document.add(infoTable);
            
            // Tabla de datos
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1.2f, 2.5f, 2f, 1.3f, 1.5f, 1.5f});
            
            // Encabezados
            BaseColor headerColor = new BaseColor(0, 123, 255);
            String[] headers = {"Tipo", "Nombre", "Categoría", "Cantidad", "Precio Unit.", "Ingreso Total"};
            
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
                headerCell.setBackgroundColor(headerColor);
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                headerCell.setPadding(8);
                table.addCell(headerCell);
            }
            
            // Datos
            boolean alternate = false;
            for (ReporteIngreso item : reportes) {
                BaseColor rowColor = alternate ? BaseColor.WHITE : new BaseColor(245, 245, 245);
                alternate = !alternate;
                
                // Tipo
                PdfPCell dataCell = new PdfPCell(new Phrase(item.getTipo(), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setPadding(5);
                table.addCell(dataCell);
                
                // Nombre
                dataCell = new PdfPCell(new Phrase(item.getNombre(), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setPadding(5);
                table.addCell(dataCell);
                
                // Categoría
                String categoria = item.getCategoria() != null ? item.getCategoria() : "-";
                dataCell = new PdfPCell(new Phrase(categoria, normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setPadding(5);
                table.addCell(dataCell);
                
                // Cantidad
                dataCell = new PdfPCell(new Phrase(String.valueOf(item.getCantidadVendida()), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                dataCell.setPadding(5);
                table.addCell(dataCell);
                
                // Precio Unitario
                dataCell = new PdfPCell(new Phrase("$" + String.format("%.2f", item.getPrecioUnitario()), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                dataCell.setPadding(5);
                table.addCell(dataCell);
                
                // Ingreso Total
                dataCell = new PdfPCell(new Phrase("$" + String.format("%.2f", item.getIngresoTotal()), normalFont));
                dataCell.setBackgroundColor(rowColor);
                dataCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                dataCell.setPadding(5);
                table.addCell(dataCell);
            }
            
            // Fila de total
            PdfPCell totalCell = new PdfPCell(new Phrase("TOTAL GENERAL", new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
            totalCell.setColspan(5);
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalCell.setPadding(8);
            totalCell.setBackgroundColor(new BaseColor(233, 236, 239));
            table.addCell(totalCell);
            
            totalCell = new PdfPCell(new Phrase("$" + String.format("%.2f", totalGeneral), 
                                               new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalCell.setPadding(8);
            totalCell.setBackgroundColor(new BaseColor(233, 236, 239));
            table.addCell(totalCell);
            
            document.add(table);
            
            // Pie de página
            Paragraph footer = new Paragraph("\nTotal de registros: " + reportes.size(), 
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