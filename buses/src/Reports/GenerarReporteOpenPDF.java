/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reports;

import ClassDAO.ReporteDAO;
import ClassVO.ReporteVO;
import ClassVO.ReporteVO.HabitacionVO;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Clase para generar reportes de tickets usando OpenPDF
 * Migrado desde JasperReports
 * 
 * @author alxyg
 */
public class GenerarReporteOpenPDF {
    
    public static final String LOGO_PATH = "http://192.168.1.29/Assets/logo_ticket.jpg";
    
    // Constantes de texto
    private static final String DIRECCION = "Blvd. Revolución esq. con Falcón #805\nCol. Centro Torreón Coah.";
    private static final String TELEFONOS = "Tel. (871) 716-72-75 Cel. 8711-87-09-86 y\n8713-15-61-23";
    private static final String POLITICA_CANCELACION = "**EN CASO DE CANCELACIÓN NO SE REEMBOLSA EL ANTICIPO**";
    private static final String POLITICA_HOTEL = "**QUEDA SUJETO A LA DISPONIBILIDAD DEL HOTEL**";
    
    // Modo debug para mostrar cálculos de altura
    private static final boolean DEBUG_ALTURA = true;
    
    /**
     * Clase auxiliar para cálculo de alturas de componentes
     */
    private static class AlturaComponentes {
        static final float LOGO = 65;
        static final float DIRECCION = 40;
        static final float ESPACIADO_ENCABEZADO = 10;
        static final float LINEA_SIMPLE = 15;
        static final float LINEA_PEQUENA = 12;
        static final float HEADER_TABLA = 15;
        static final float FILA_TABLA = 15;
        static final float TOTALES = 60;
        static final float FIRMA = 50;
        static final float POLITICAS = 65;
        static final float MARGEN_SEGURIDAD = 45;
        
        /**
         * Calcula caracteres aproximados por línea
         */
        static int caracteresPorLinea(float anchoDisponible, float fontSize) {
            return (int) (anchoDisponible / (0.6 * fontSize));
        }
        
        /**
         * Calcula líneas necesarias para un texto
         */
        static int lineasNecesarias(String texto, float anchoDisponible, float fontSize) {
            if (texto == null || texto.isEmpty()) return 1;
            int caracteresPorLinea = caracteresPorLinea(anchoDisponible, fontSize);
            return (int) Math.ceil((double) texto.length() / caracteresPorLinea);
        }
    }
    
    /**
     * Calcula la altura necesaria del documento basándose en el contenido dinámico
     */
    private static float calcularAlturaDocumento(ReporteVO reporte, boolean esRedondo, boolean tieneHabitaciones) {
        float altura = 0;
        float anchoDisponible = 200; // 240 - 40 de márgenes (20 por lado)
        
        // ENCABEZADO
        altura += AlturaComponentes.LOGO;
        altura += AlturaComponentes.DIRECCION;
        altura += AlturaComponentes.ESPACIADO_ENCABEZADO;
        
        // FOLIO Y FECHA
        altura += 2 * AlturaComponentes.LINEA_SIMPLE;
        altura += 5;
        
        // TELÉFONO
        altura += AlturaComponentes.LINEA_SIMPLE;
        altura += 5;
        
        // NOMBRE DEL CLIENTE
        altura += AlturaComponentes.LINEA_SIMPLE; // Label
        String nombreCliente = reporte.getNombreCliente();
        int lineasNombre = AlturaComponentes.lineasNecesarias(nombreCliente, anchoDisponible, 10);
        altura += lineasNombre * AlturaComponentes.LINEA_SIMPLE;
        altura += 5;
        
        if (DEBUG_ALTURA) {
            System.out.println("Nombre: " + nombreCliente.length() + " chars → " + lineasNombre + " líneas");
        }
        // NOMBRE DEL DESTINO
        altura += AlturaComponentes.LINEA_SIMPLE; // Label
        String nombreDestino = reporte.getCiudad().toUpperCase();
        int lineasDestino = AlturaComponentes.lineasNecesarias(nombreDestino, anchoDisponible, 10);
        altura += lineasDestino * AlturaComponentes.LINEA_SIMPLE;
        altura += 20;
        if (DEBUG_ALTURA) {
            System.out.println("Ciudad: " + nombreDestino.length() + " chars → " + lineasDestino + " líneas");
        }
        // DETALLES DEL VIAJE (cantidad, anticipo, lugar, viaje, partiendo, día, hora)
        altura += 7 * AlturaComponentes.LINEA_SIMPLE;
        
        // Si es redondo, agregar datos de regreso
        if (esRedondo) {
            altura += 3 * AlturaComponentes.LINEA_SIMPLE;
        }
        
        // OBSERVACIONES
        altura += AlturaComponentes.LINEA_SIMPLE; // Label
        String obs = reporte.getObservaciones();
        if (obs != null && !obs.isEmpty()) {
            int lineasObs = AlturaComponentes.lineasNecesarias(obs, anchoDisponible, 10);
            altura += lineasObs * AlturaComponentes.LINEA_PEQUENA;
            
            if (DEBUG_ALTURA) {
                System.out.println("Observaciones: " + obs.length() + " chars → " + lineasObs + " líneas");
            }
        } else {
            altura += AlturaComponentes.LINEA_PEQUENA;
        }
        altura += 5;
        
        // MÉTODO DE PAGO Y VENDEDOR
        altura += 2 * AlturaComponentes.LINEA_SIMPLE;
        altura += 5;
        
        // LUGARES ASIGNADOS
        altura += AlturaComponentes.LINEA_SIMPLE; // Label
        String asientos = reporte.getAsientos();
        if (asientos != null) {
            int lineasAsientos = AlturaComponentes.lineasNecesarias(asientos, anchoDisponible, 10);
            altura += lineasAsientos * AlturaComponentes.LINEA_SIMPLE;
            
            if (DEBUG_ALTURA) {
                System.out.println("Asientos: " + asientos.length() + " chars → " + lineasAsientos + " líneas");
            }
        }
        altura += 10;
        
        // TABLA DE HABITACIONES
        if (tieneHabitaciones && !reporte.getHabitaciones().isEmpty()) {
            altura += AlturaComponentes.LINEA_SIMPLE; // Label
            altura += AlturaComponentes.HEADER_TABLA;
            altura += reporte.getHabitaciones().size() * AlturaComponentes.FILA_TABLA;
            altura += 10;
            
            if (DEBUG_ALTURA) {
                System.out.println("Habitaciones: " + reporte.getHabitaciones().size() + " filas");
            }
        }
        
        // TOTALES
        altura += AlturaComponentes.TOTALES;
        
        // FIRMA
        altura += AlturaComponentes.FIRMA;
        
        // POLÍTICAS
        altura += AlturaComponentes.POLITICAS;
        
        // MARGEN DE SEGURIDAD
        altura += AlturaComponentes.MARGEN_SEGURIDAD;
        
        return altura;
    }
    /**
     * Genera un nombre de archivo único con timestamp
     * 
     * @param tipo Tipo de ticket (Redondo, Sencillo, RedondoHabitaciones)
     * @param folio Número de folio
     * @return Nombre de archivo único
     */
    public static String generarNombreUnico(String tipo, Long folio) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "Ticket_" + tipo + "_" + folio + "_" + timestamp + ".pdf";
    }
    /**
     * Genera un ticket con viaje redondo y habitaciones
     * Migrado de ReporteTicketRedondoConHabitaciones.jrxml
     * 
     * @param idCliente ID del cliente
     * @param idViaje ID del viaje
     * @param cliente Nombre del cliente
     * @param ciudad Ciudad de destino
     * @param estado Estado de destino
     * @param fecha Fecha de salida
     * @param idDetalle Folio del ticket
     */
    public static void reporteTicketRedondoConHabitaciones(int idCliente, int idViaje, String cliente, 
                                                           String ciudad, String estado, String fecha, Long idDetalle) {
        ReporteDAO dao = new ReporteDAO();
        ReporteVO reporte = dao.obtenerDatosTicketCompleto(idCliente, idViaje, idDetalle, cliente, ciudad, estado);
        
        if (reporte == null) {
            System.err.println("No se encontraron datos para el ticket con ID: " + idDetalle);
            return;
        }
        
        //String nombreArchivo = "Ticket_Redondo_Habitaciones_" + idDetalle + ".pdf";
        String nombreArchivo = generarNombreUnico("Redondo_Habitaciones", idDetalle);
        Document document = new Document();
        
        try {
            // ========== CALCULAR ALTURA DINÁMICA ==========
            float alturaBase = 760;  // Altura mínima del tipo de ticket
            float alturaCalculada = calcularAlturaDocumento(reporte, true, true);
            float alturaFinal = Math.max(alturaBase, alturaCalculada);
            
            if (DEBUG_ALTURA) {
                System.out.println("========== TICKET REDONDO CON HABITACIONES ==========");
                System.out.println("Altura base: " + alturaBase);
                System.out.println("Altura calculada: " + alturaCalculada);
                System.out.println("Altura final: " + alturaFinal);
                System.out.println("====================================================");
            }
            
            // ========== CREAR DOCUMENTO CON ALTURA DINÁMICA ==========
            Rectangle pageSize = new Rectangle(239, alturaFinal);
            document.setPageSize(pageSize);
            document.setMargins(20, 20, 20, 20);
            
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();
            
            // ========== ENCABEZADO ==========
            agregarEncabezado(document);
            
            // ========== FOLIO Y FECHA ==========
            document.add(crearParrafoCentrado("FOLIO: " + reporte.getFolio(), 10, Font.BOLD));
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearParrafoCentrado("FECHA: " + reporte.getFechaVenta(), 10, Font.BOLD));
            
            // ========== TELÉFONO ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(crearParrafo("TELÉFONO: " + reporte.getTelefono(), 10, Font.BOLD));
            
            // ========== DATOS DEL CLIENTE ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 4)));
            document.add(crearParrafo("RECIBIMOS DEL CLIENTE: ", 10, Font.NORMAL));
            document.add(crearParrafo(reporte.getNombreCliente().toUpperCase(), 10, Font.BOLD));
            
            // ========== DETALLES DEL VIAJE ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(crearLineaDinamica("LA CANTIDAD DE $ ", String.valueOf(reporte.getAnticipo())));
            document.add(crearLineaDinamica("POR ANTICIPO A CUENTA DE ", String.valueOf(reporte.getNumeroPersonas())));
            document.add(crearLineaDinamica("LUGAR(ES) A ", reporte.getCiudad().toUpperCase()));
            document.add(crearLineaDinamica("EN VIAJE ", reporte.getViaje().toUpperCase()));
            document.add(crearLineaDinamica("PARTIENDO DE ", reporte.getSube().toUpperCase()));
            
            // Fecha y hora de salida
            document.add(crearLineaDinamica("EL DÍA ", fecha + "  A LAS  " + reporte.getHora()));
            
            // Fecha y hora de regreso
            document.add(crearLineaDinamica("REGRESANDO DE ", reporte.getCiudad().toUpperCase()));
            document.add(crearLineaDinamica("EL DÍA ", reporte.getFechaRegreso() + "  A LAS  " + reporte.getHoraRegreso()));
            
            // ========== OBSERVACIONES ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(crearParrafo("OBSERVACIONES: ", 10, Font.NORMAL));
            String observaciones = (reporte.getObservaciones() != null) ? reporte.getObservaciones() : "";
            document.add(crearParrafo(observaciones, 10, Font.BOLD));
            
            // ========== MÉTODO DE PAGO Y VENDEDOR ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 4)));
            document.add(crearLineaDinamica("MÉTODO DE PAGO: ", reporte.getPago().toUpperCase()));
            document.add(crearLineaDinamica("VENDEDOR: ", reporte.getUsuario().toUpperCase()));
            
            // ========== LUGARES ASIGNADOS ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(crearParrafo("LUGARES ASIGNADOS: ", 9, Font.ITALIC));
            Paragraph asientos;
            asientos = new Paragraph(reporte.getAsientos(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD | Font.UNDERLINE));
            document.add(asientos);
            
            // ========== HABITACIONES (TABLA) ==========
            if (!reporte.getHabitaciones().isEmpty()) {
                document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 5)));
                document.add(crearParrafo("Habitaciones: ", 10, Font.ITALIC));
                document.add(crearTablaHabitaciones(reporte.getHabitaciones()));
            }
            
            // ========== TOTALES ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 5)));
            document.add(crearLineaDinamica("TOTAL: ", String.valueOf(reporte.getCosto())));
            document.add(crearLineaDinamica("ANTICIPO: ", String.valueOf(reporte.getAnticipo())));
            document.add(crearLineaDinamica("RESTANTE: ", String.valueOf(reporte.getRestante())));
            
            // ========== FIRMA DEL CLIENTE ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(new LineSeparator());
            document.add(crearParrafoCentrado("FIRMA DEL CLIENTE", 10, Font.NORMAL));
            
            // ========== POLÍTICAS ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            document.add(crearParrafoCentrado(POLITICA_CANCELACION, 10, Font.UNDERLINE));
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(crearParrafoCentrado(POLITICA_HOTEL, 10, Font.UNDERLINE));
            
            document.close();
            Desktop.getDesktop().open(new File(nombreArchivo));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Genera un ticket con viaje redondo sin habitaciones
     * Migrado de ReporteTicket.jrxml
     * 
     * @param idCliente ID del cliente
     * @param idViaje ID del viaje
     * @param cliente Nombre del cliente
     * @param ciudad Ciudad de destino
     * @param estado Estado de destino
     * @param fecha Fecha de salida
     * @param idDetalle Folio del ticket
     */
    public static void reporteTicketRedondo(int idCliente, int idViaje, String cliente, 
                                           String ciudad, String estado, String fecha, Long idDetalle) {
        ReporteDAO dao = new ReporteDAO();
        ReporteVO reporte = dao.obtenerDatosTicketCompleto(idCliente, idViaje, idDetalle, cliente, ciudad, estado);
        
        if (reporte == null) {
            System.err.println("No se encontraron datos para el ticket con ID: " + idDetalle);
            return;
        }
        
        //String nombreArchivo = "Ticket_Redondo_" + idDetalle + ".pdf";
        String nombreArchivo = generarNombreUnico("Redondo_", idDetalle);
        Document document = new Document();
        
        try {
            // ========== CALCULAR ALTURA DINÁMICA ==========
            float alturaBase = 700;
            float alturaCalculada = calcularAlturaDocumento(reporte, true, false);
            float alturaFinal = Math.max(alturaBase, alturaCalculada);
            
            if (DEBUG_ALTURA) {
                System.out.println("========== TICKET REDONDO ==========");
                System.out.println("Altura base: " + alturaBase);
                System.out.println("Altura calculada: " + alturaCalculada);
                System.out.println("Altura final: " + alturaFinal);
                System.out.println("====================================");
            }
            
            // ========== CREAR DOCUMENTO CON ALTURA DINÁMICA ==========
            Rectangle pageSize = new Rectangle(240, alturaFinal);
            document.setPageSize(pageSize);
            document.setMargins(20, 20, 20, 20);
            
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();
            
            // ========== ENCABEZADO ==========
            agregarEncabezado(document);
            
            // ========== FOLIO Y FECHA ==========
            document.add(crearParrafoCentrado("FOLIO: " + reporte.getFolio(), 10, Font.BOLD));
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearParrafoCentrado("FECHA: " + reporte.getFechaVenta(), 10, Font.BOLD));
            
            // ========== TELÉFONO ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(crearParrafo("TELÉFONO: " + reporte.getTelefono(), 10, Font.BOLD));
            
            // ========== DATOS DEL CLIENTE ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearParrafo("RECIBIMOS DEL CLIENTE: ", 10, Font.NORMAL));
            document.add(crearParrafo(reporte.getNombreCliente().toUpperCase(), 10, Font.BOLD));
            
            // ========== DETALLES DEL VIAJE ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearLineaDinamica("LA CANTIDAD DE $ ", String.valueOf(reporte.getAnticipo())));
            document.add(crearLineaDinamica("POR ANTICIPO A CUENTA DE ", String.valueOf(reporte.getNumeroPersonas())));
            document.add(crearLineaDinamica("LUGAR(ES) A ", reporte.getCiudad().toUpperCase()));
            document.add(crearLineaDinamica("EN VIAJE ", reporte.getViaje().toUpperCase()));
            document.add(crearLineaDinamica("PARTIENDO DE ", reporte.getSube().toUpperCase()));
            
            // Fecha y hora de salida
            document.add(crearLineaDinamica("EL DÍA ", fecha + "  A LAS  " + reporte.getHora()));
            
            // Fecha y hora de regreso
            document.add(crearLineaDinamica("REGRESANDO DE ", reporte.getCiudad().toUpperCase()));
            document.add(crearLineaDinamica("EL DÍA ", reporte.getFechaRegreso() + "  A LAS  " + reporte.getHoraRegreso()));
            
            // ========== OBSERVACIONES ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearParrafo("OBSERVACIONES: ", 10, Font.NORMAL));
            String observaciones = (reporte.getObservaciones() != null) ? reporte.getObservaciones() : "";
            document.add(crearParrafo(observaciones, 10, Font.BOLD));
            
            // ========== MÉTODO DE PAGO Y VENDEDOR ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(crearLineaDinamica("MÉTODO DE PAGO: ", reporte.getPago().toUpperCase()));
            document.add(crearLineaDinamica("VENDEDOR: ", reporte.getUsuario().toUpperCase()));
            
            // ========== LUGARES ASIGNADOS ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearParrafo("LUGARES ASIGNADOS: ", 9, Font.ITALIC));
            Paragraph asientos = new Paragraph(reporte.getAsientos(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD | Font.UNDERLINE));
            document.add(asientos);
            
            // ========== FIRMA DEL CLIENTE ==========
            document.add(new Paragraph("\n\n\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            document.add(new LineSeparator());
            document.add(crearParrafoCentrado("FIRMA DEL CLIENTE", 10, Font.NORMAL));
            
            // ========== TOTALES ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 5)));
            document.add(crearLineaDinamica("TOTAL: ", String.valueOf(reporte.getCosto())));
            document.add(crearLineaDinamica("ANTICIPO: ", String.valueOf(reporte.getAnticipo())));
            document.add(crearLineaDinamica("RESTANTE: ", String.valueOf(reporte.getRestante())));
            
            // ========== POLÍTICAS ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            document.add(crearParrafoCentrado(POLITICA_CANCELACION, 10, Font.UNDERLINE));
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(crearParrafoCentrado(POLITICA_HOTEL, 10, Font.UNDERLINE));
            
            document.close();
            Desktop.getDesktop().open(new File(nombreArchivo));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Genera un ticket con viaje sencillo
     * Migrado de ReporteTicketSencillo.jrxml
     * 
     * @param idCliente ID del cliente
     * @param idViaje ID del viaje
     * @param cliente Nombre del cliente
     * @param ciudad Ciudad de destino
     * @param estado Estado de destino
     * @param fecha Fecha de salida
     * @param idDetalle Folio del ticket
     */
    public static void reporteTicketSencillo(int idCliente, int idViaje, String cliente, 
                                            String ciudad, String estado, String fecha, Long idDetalle) {
        ReporteDAO dao = new ReporteDAO();
        ReporteVO reporte = dao.obtenerDatosTicketCompleto(idCliente, idViaje, idDetalle, cliente, ciudad, estado);
        
        if (reporte == null) {
            System.err.println("No se encontraron datos para el ticket con ID: " + idDetalle);
            return;
        }
        
        //String nombreArchivo = "Ticket_Sencillo_" + idDetalle + ".pdf";
        String nombreArchivo = generarNombreUnico("Sencillo", idDetalle);
        Document document = new Document();
        
        try {
            // ========== CALCULAR ALTURA DINÁMICA ==========
            float alturaBase = 650;
            float alturaCalculada = calcularAlturaDocumento(reporte, false, false);
            float alturaFinal = Math.max(alturaBase, alturaCalculada);
            
            if (DEBUG_ALTURA) {
                System.out.println("========== TICKET SENCILLO ==========");
                System.out.println("Altura base: " + alturaBase);
                System.out.println("Altura calculada: " + alturaCalculada);
                System.out.println("Altura final: " + alturaFinal);
                System.out.println("=====================================");
            }
            
            // ========== CREAR DOCUMENTO CON ALTURA DINÁMICA ==========
            Rectangle pageSize = new Rectangle(240, alturaFinal);
            document.setPageSize(pageSize);
            document.setMargins(20, 20, 20, 20);
            
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();
            
            // ========== ENCABEZADO ==========
            agregarEncabezado(document);
            
            // ========== FOLIO Y FECHA ==========
            document.add(crearParrafoCentrado("FOLIO: " + reporte.getFolio(), 10, Font.BOLD));
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearParrafoCentrado("FECHA: " + reporte.getFechaVenta(), 10, Font.BOLD));
            
            // ========== TELÉFONO ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearParrafo("TELÉFONO: " + reporte.getTelefono(), 10, Font.BOLD));
            
            // ========== DATOS DEL CLIENTE ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearParrafo("RECIBIMOS DEL CLIENTE ", 10, Font.NORMAL));
            document.add(crearParrafo(reporte.getNombreCliente().toUpperCase(), 10, Font.BOLD));
            
            // ========== DETALLES DEL VIAJE ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearLineaDinamica("LA CANTIDAD DE $ ", String.valueOf(reporte.getAnticipo())));
            document.add(crearLineaDinamica("POR ANTICIPO A CUENTA DE ", String.valueOf(reporte.getNumeroPersonas())));
            document.add(crearLineaDinamica("LUGAR(ES) A ", reporte.getCiudad().toUpperCase()));
            document.add(crearLineaDinamica("EN VIAJE ", reporte.getViaje().toUpperCase()));
            document.add(crearLineaDinamica("PARTIENDO DE ", reporte.getSube().toUpperCase()));
            
            // Fecha y hora de salida
            document.add(crearLineaDinamica("EL DÍA ", fecha + "  A LAS  " + reporte.getHora()));
            
            // ========== OBSERVACIONES ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearParrafo("OBSERVACIONES: ", 10, Font.NORMAL));
            String observaciones = (reporte.getObservaciones() != null) ? reporte.getObservaciones() : "";
            document.add(crearParrafo(observaciones, 10, Font.BOLD));
            
            // ========== MÉTODO DE PAGO Y VENDEDOR ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(crearLineaDinamica("MÉTODO DE PAGO: ", reporte.getPago().toUpperCase()));
            document.add(crearLineaDinamica("VENDEDOR: ", reporte.getUsuario().toUpperCase()));
            
            // ========== LUGARES ASIGNADOS ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 2)));
            document.add(crearParrafo("LUGARES ASIGNADOS: ", 9, Font.ITALIC));
            Paragraph asientos = new Paragraph(reporte.getAsientos(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD | Font.UNDERLINE));
            document.add(asientos);
            
            // ========== FIRMA DEL CLIENTE ==========
            document.add(new Paragraph("\n\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            document.add(new LineSeparator());
            document.add(crearParrafoCentrado("FIRMA DEL CLIENTE", 10, Font.NORMAL));
            
            // ========== TOTALES ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 5)));
            document.add(crearLineaDinamica("TOTAL: ", String.valueOf(reporte.getCosto())));
            document.add(crearLineaDinamica("ANTICIPO: ", String.valueOf(reporte.getAnticipo())));
            document.add(crearLineaDinamica("RESTANTE: ", String.valueOf(reporte.getRestante())));
            
            // ========== POLÍTICAS ==========
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            document.add(crearParrafoCentrado(POLITICA_CANCELACION, 10, Font.UNDERLINE));
            document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
            document.add(crearParrafoCentrado(POLITICA_HOTEL, 10, Font.UNDERLINE));
            
            document.close();
            Desktop.getDesktop().open(new File(nombreArchivo));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Método principal que decide automáticamente qué tipo de reporte generar
     * basándose en el tipo de viaje y si tiene habitaciones
     * 
     * @param idCliente ID del cliente
     * @param idViaje ID del viaje
     * @param cliente Nombre del cliente
     * @param ciudad Ciudad de destino
     * @param estado Estado de destino
     * @param fecha Fecha de salida
     * @param idDetalle Folio del ticket
     */
    public static void reporteTicketAutomatico(int idCliente, int idViaje, String cliente, 
                                              String ciudad, String estado, String fecha, Long idDetalle) {
        ReporteDAO dao = new ReporteDAO();
        ReporteVO reporte = dao.obtenerDatosTicketCompleto(idCliente, idViaje, idDetalle, cliente, ciudad, estado);
        
        if (reporte == null) {
            System.err.println("No se encontraron datos para el ticket con ID: " + idDetalle);
            return;
        }
        
        // Determinar el tipo de reporte a generar
        boolean esRedondo = dao.esViajeRedondo(idCliente, idViaje, idDetalle);
        boolean tieneHabitaciones = !reporte.getHabitaciones().isEmpty();
        
        if (esRedondo && tieneHabitaciones) {
            reporteTicketRedondoConHabitaciones(idCliente, idViaje, cliente, ciudad, estado, fecha, idDetalle);
        } else if (esRedondo) {
            reporteTicketRedondo(idCliente, idViaje, cliente, ciudad, estado, fecha, idDetalle);
        } else {
            reporteTicketSencillo(idCliente, idViaje, cliente, ciudad, estado, fecha, idDetalle);
        }
    }

    // ========== MÉTODOS DE AYUDA PARA DISEÑO ==========
    
    /**
     * Agrega el encabezado con logo y dirección
     */
    private static void agregarEncabezado(Document document) throws Exception {
        try {
            Image logo = Image.getInstance(LOGO_PATH);
            logo.setAlignment(Element.ALIGN_CENTER);
            logo.scaleAbsolute(162, 65);
            document.add(logo);
        } catch (Exception e) {
            System.err.println("No se pudo cargar el logo: " + e.getMessage());
        }
        
        document.add(crearParrafoCentrado(DIRECCION, 10, Font.NORMAL));
        document.add(crearParrafoCentrado(TELEFONOS, 9, Font.NORMAL));
        document.add(new Paragraph("\n", FontFactory.getFont(FontFactory.HELVETICA, 3)));
    }
    
    /**
     * Crea un párrafo centrado
     */
    private static Paragraph crearParrafoCentrado(String texto, float size, int estilo) {
        Paragraph p = new Paragraph(texto, FontFactory.getFont(FontFactory.HELVETICA, size, estilo));
        p.setAlignment(Element.ALIGN_CENTER);
        return p;
    }
    
    /**
     * Crea un párrafo normal
     */
    private static Paragraph crearParrafo(String texto, float size, int estilo) {
        Paragraph p = new Paragraph(texto, FontFactory.getFont(FontFactory.HELVETICA, size, estilo));
        p.setSpacingBefore(2f);
        return p;
    }
    
    /**
     * Crea una línea con label normal y valor en negrita
     */
    private static Paragraph crearLineaDinamica(String label, String valor) {
        Paragraph p = new Paragraph();
        p.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL));
        p.add(label);
        p.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
        p.add(valor);
        p.setSpacingBefore(2f);
        return p;
    }
    
    /**
     * Crea una tabla con las habitaciones
     * Migrado del subreporte ReporteHabitaciones.jrxml
     */
    private static PdfPTable crearTablaHabitaciones(List<HabitacionVO> habitaciones) throws Exception {
        PdfPTable tabla = new PdfPTable(2);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{3f, 1f});
        
        // Encabezados
        PdfPCell headerHotel = new PdfPCell(new Phrase("Hotel", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
        headerHotel.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerHotel.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerHotel.setPadding(3);
        headerHotel.setBorderWidth(1);
        tabla.addCell(headerHotel);
        
        PdfPCell headerCantidad = new PdfPCell(new Phrase("Cantidad", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
        headerCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCantidad.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerCantidad.setPadding(3);
        headerCantidad.setBorderWidth(1);
        tabla.addCell(headerCantidad);
        
        // Datos
        for (HabitacionVO habitacion : habitaciones) {
            PdfPCell cellHotel = new PdfPCell(new Phrase(habitacion.getNombreHotel(), FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL)));
            cellHotel.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHotel.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellHotel.setPadding(3);
            cellHotel.setBorderWidth(1);
            tabla.addCell(cellHotel);
            
            PdfPCell cellCantidad = new PdfPCell(new Phrase(String.valueOf(habitacion.getCantidad()), FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL)));
            cellCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCantidad.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellCantidad.setPadding(3);
            cellCantidad.setBorderWidth(1);
            tabla.addCell(cellCantidad);
        }
        
        return tabla;
    }
}