package Control;

import ClassDAO.AbonosDAO;
import ClassDAO.AbonosDAO.ResultadoAbono;
import ClassDAO.DetalleDAO;
import ClassVO.AbonosVO;
import ClassVO.DetalleVO;
import ClassVO.UsuarioVO;
import Reports.GenerarReporte;
import Tables.TablaAbonos;
import Tables.TablaDetalles;
import Vista.Abonos;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * ControladorAbonos - REFACTORIZADO CON SEPARACIÓN DE RESPONSABILIDADES
 * 
 * RESPONSABILIDADES DEL CONTROLADOR:
 * - Coordinar entre Vista y DAO
 * - Validar datos de entrada de la UI
 * - Actualizar la interfaz
 * - Manejar eventos de usuario
 * 
 * LO QUE NO HACE:
 * - ❌ SQL directo
 * - ❌ Manejo de Connection, PreparedStatement, ResultSet
 * - ❌ Transacciones (eso es responsabilidad del DAO)
 * 
 * @author alxyg
 */
public class ControladorAbonos implements ActionListener, MouseListener, KeyListener {
    
    private final Abonos vista;
    private final UsuarioVO usuario;
    private final AbonosDAO modeloAbonos;
    private final DetalleDAO modeloDetalle;
    private final TablaDetalles tablaDetallesHelper = new TablaDetalles();
    private final TablaAbonos tablaAbonosHelper = new TablaAbonos();
    
    // Control de paginación
    private int paginaActual = 1;
    private static final int REGISTROS_POR_PAGINA = 20;

    public ControladorAbonos(Abonos abonos, UsuarioVO usuario) {
        this.vista = abonos;
        this.usuario = usuario;
        this.modeloAbonos = new AbonosDAO();
        this.modeloDetalle = new DetalleDAO();

        // Registro de Eventos
        this.vista.lbl_back.addMouseListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.rsbtnFormularioEnviar.addActionListener(this);
        this.vista.btnAnterior.addActionListener(this);
        this.vista.btnSiguiente.addActionListener(this);
        this.vista.tablaVentas.addMouseListener(this);
        this.vista.txtBuscar.addKeyListener(this);
    }

    public void iniciar() {
        vista.setTitle("Gestión de Abonos - Viajes");
        vista.setLocationRelativeTo(null);
        llenarMetodosPago();
        vista.setVisible(true);
        cargarTablaPaginada();
        actualizarBotonesPaginacion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscar) {
            ejecutarBusqueda();
        }
        if (e.getSource() == vista.rsbtnFormularioEnviar) {
            procesarNuevoAbono();
        }
        if (e.getSource() == vista.btnAnterior) {
            paginaAnterior();
        }
        if (e.getSource() == vista.btnSiguiente) {
            paginaSiguiente();
        }
    }

    // ====================================================================
    // PAGINACIÓN
    // ====================================================================
    
    private void cargarTablaPaginada() {
        int offset = (paginaActual - 1) * REGISTROS_POR_PAGINA;
        ArrayList<DetalleVO> ventas = modeloDetalle.listarVentasPaginadas(offset, REGISTROS_POR_PAGINA);
        tablaDetallesHelper.cargarTablaDetalleVistaAbonos(vista.tablaVentas, ventas);
        
        System.out.println("[PAGINACIÓN] Página " + paginaActual + 
                           " - Mostrando " + ventas.size() + " registros");
    }
    
    private void paginaAnterior() {
        if (paginaActual > 1) {
            paginaActual--;
            cargarTablaPaginada();
            actualizarBotonesPaginacion();
        }
    }
    
    private void paginaSiguiente() {
        paginaActual++;
        cargarTablaPaginada();
        actualizarBotonesPaginacion();
    }
    
    private void actualizarBotonesPaginacion() {
        vista.btnAnterior.setEnabled(paginaActual > 1);
        
        int totalVentas = modeloDetalle.contarVentasActivas();
        int totalPaginas = (int) Math.ceil((double) totalVentas / REGISTROS_POR_PAGINA);
        vista.btnSiguiente.setEnabled(paginaActual < totalPaginas);
        
        System.out.println("[PAGINACIÓN] Página " + paginaActual + " de " + totalPaginas);
    }

    // ====================================================================
    // REGISTRO DE ABONO (LIMPIO - SIN SQL)
    // ====================================================================
    
    /**
     * ✅ REFACTORIZADO: Proceso limpio sin SQL
     * 
     * El controlador solo:
     * 1. Valida entrada del usuario
     * 2. Llama al DAO para hacer el registro
     * 3. Actualiza la interfaz según el resultado
     */
    private void procesarNuevoAbono() {
        // ========== VALIDACIÓN DE ENTRADA ==========
        if (vista.lblFolio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione primero una venta de la tabla.");
            return;
        }

        double monto;
        try {
            monto = Double.parseDouble(vista.txtMonto.getText());
            if (monto <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Ingrese un monto válido mayor a 0.");
            return;
        }
        
        Long idDetalle = Long.parseLong(vista.lblFolio.getText());
        
        // ========== OBTENER DATOS DE LA VENTA (del DAO) ==========
        DetalleVO venta = modeloDetalle.encontrar(idDetalle);
        if (venta == null) {
            JOptionPane.showMessageDialog(vista, "Error: No se encontró la venta.");
            return;
        }
        
        // ========== VALIDACIÓN DE NEGOCIO ==========
        double saldoRestante = venta.getCosto() - venta.getAnticipo();
        if (monto > saldoRestante) {
            JOptionPane.showMessageDialog(vista,
                String.format("Error: El monto excede el saldo restante.\n" +
                              "Costo: $%.2f\n" +
                              "Anticipo actual: $%.2f\n" +
                              "Saldo: $%.2f",
                              venta.getCosto(), venta.getAnticipo(), saldoRestante),
                "Validación de Pago",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        vista.rsbtnFormularioEnviar.setEnabled(false);
        
        try {
            // ========== PREPARAR VO CON DATOS DEL ABONO ==========
            AbonosVO nuevoAbono = new AbonosVO();
            nuevoAbono.setMonto(monto);
            nuevoAbono.setMetodoPago(vista.cbxPago.getSelectedItem().toString());
            nuevoAbono.setIdDetalle(idDetalle);
            nuevoAbono.setIdUsuario(usuario.getId());
            
            // ========== LLAMAR AL DAO (toda la lógica SQL está allí) ==========
            ResultadoAbono resultado = modeloAbonos.registrarAbonoTransaccional(
                nuevoAbono,
                venta.getCosto(),
                venta.getAnticipo()
            );
            
            // ========== PROCESAR RESULTADO ==========
            if (resultado.isExito()) {
                // Mostrar mensaje de éxito
                String mensaje;
                if (resultado.isLiquidado()) {
                    mensaje = "¡VENTA LIQUIDADA COMPLETAMENTE! 🎉\n\n" +
                             "El cliente ha completado el pago total.\n" +
                             "Saldo restante: $0.00";
                } else {
                    mensaje = String.format(
                        "Abono registrado correctamente.\n\n" +
                        "Monto abonado: $%.2f\n" +
                        "Anticipo total: $%.2f\n" +
                        "Saldo restante: $%.2f",
                        monto, resultado.getNuevoAnticipo(), resultado.getNuevoSaldo()
                    );
                }
                
                JOptionPane.showMessageDialog(vista, mensaje,
                    resultado.isLiquidado() ? "¡Venta Liquidada!" : "Abono Registrado",
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Actualizar interfaz
                actualizarInterfazDespuesDeAbono(
                    idDetalle,
                    resultado.getNuevoAnticipo(),
                    resultado.getNuevoSaldo()
                );
                
                // Ofrecer imprimir ticket
                preguntarImprimirTicket(idDetalle, resultado.isLiquidado());
                
            } else {
                // Mostrar error
                JOptionPane.showMessageDialog(vista,
                    "Error al registrar el abono:\n" + resultado.getMensaje(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } finally {
            vista.rsbtnFormularioEnviar.setEnabled(true);
        }
    }
    
    /**
     * Actualiza la interfaz después de registrar un abono
     */
    private void actualizarInterfazDespuesDeAbono(Long idDetalle, double nuevoAnticipo, double nuevoSaldo) {
        // Limpiar formulario
        limpiarFormularioAbono();
        
        // Actualizar labels
        vista.lblTotalAbonado.setText(String.format("$ %.2f", nuevoAnticipo));
        vista.lblSaldoRestante.setText(String.format("$ %.2f", nuevoSaldo));
        
        // Recargar historial
        cargarTablaHistorial(idDetalle);
        
        // Recargar página actual (para ver anticipo actualizado en la tabla)
        cargarTablaPaginada();
    }

    // ====================================================================
    // GENERACIÓN DE TICKET
    // ====================================================================
    
    private void preguntarImprimirTicket(Long idDetalle, boolean liquidado) {
        try {
            String mensajePregunta;
            if (liquidado) {
                mensajePregunta = "La venta ha sido liquidada completamente.\n\n" +
                                 "¿Desea imprimir el ticket actualizado?";
            } else {
                mensajePregunta = "El abono ha sido registrado exitosamente.\n\n" +
                                 "¿Desea imprimir el ticket con el saldo actualizado?";
            }
            
            int respuesta = JOptionPane.showConfirmDialog(
                vista,
                mensajePregunta,
                "Imprimir Ticket",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (respuesta == JOptionPane.YES_OPTION) {
                generarTicket(idDetalle);
            }
            
        } catch (Exception e) {
            System.err.println("Error al preguntar por impresión: " + e.getMessage());
        }
    }
    
    private void generarTicket(Long idDetalle) {
        try {
            System.out.println("[TICKET] Generando para folio " + idDetalle);
            
            DetalleVO venta = modeloDetalle.encontrar(idDetalle);
            
            if (venta == null) {
                JOptionPane.showMessageDialog(vista,
                    "Error: No se encontró la información de la venta.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            GenerarReporte.reporteTicket(
                venta.getIdCliente(),
                venta.getIdViaje(),
                venta.getNombreCliente(),
                venta.getCiudad(),
                venta.getEstado(),
                venta.getFechaVenta(),
                idDetalle
            );
            
            System.out.println("[TICKET] ✓ Generado exitosamente");
            
        } catch (Exception e) {
            System.err.println("[ERROR] Error al generar ticket: " + e.getMessage());
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(vista,
                "Error al generar el ticket:\n" + e.getMessage(),
                "Error de Impresión",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // ====================================================================
    // INTERFAZ DE USUARIO
    // ====================================================================
    
    private void ejecutarBusqueda() {
        String criterio = vista.txtBuscar.getText().trim();
        if (criterio.isEmpty()) {
            paginaActual = 1;
            cargarTablaPaginada();
            actualizarBotonesPaginacion();
        } else {
            ArrayList<DetalleVO> resultados = modeloDetalle.buscarVentas(criterio);
            tablaDetallesHelper.cargarTablaDetalleVistaAbonos(vista.tablaVentas, resultados);
            
            vista.btnAnterior.setEnabled(false);
            vista.btnSiguiente.setEnabled(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.lbl_back) {
            abrirMenu();
        }
        
        if (e.getSource() == vista.tablaVentas) {
            int fila = vista.tablaVentas.getSelectedRow();
            if (fila != -1) {
                try {
                    Long idDetalle = Long.valueOf(vista.tablaVentas.getValueAt(fila, 0).toString());
                    String nombreCliente = vista.tablaVentas.getValueAt(fila, 3).toString();
                    String costoStr = vista.tablaVentas.getValueAt(fila, 6).toString();
                    String anticipoStr = vista.tablaVentas.getValueAt(fila, 7).toString();

                    // Llenar formulario
                    vista.lblFolio.setText(idDetalle.toString());
                    vista.lblCliente.setText(nombreCliente);
                    vista.lblCosto.setText("$ " + costoStr);
                    
                    // Labels del historial
                    vista.lblAbonoFolio.setText(idDetalle.toString());
                    vista.lblAbonoCliente.setText(nombreCliente);
                    
                    // Calcular saldo
                    double costo = Double.parseDouble(costoStr);
                    double anticipo = Double.parseDouble(anticipoStr);
                    double saldo = costo - anticipo;
                    
                    vista.lblTotalAbonado.setText(String.format("$ %.2f", anticipo));
                    vista.lblSaldoRestante.setText(String.format("$ %.2f", saldo));

                    // Cargar historial
                    cargarTablaHistorial(idDetalle);
                    
                } catch (Exception ex) {
                    System.err.println("Error al cargar venta: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

    private void limpiarFormularioAbono() {
        vista.txtMonto.setText("");
        vista.cbxPago.setSelectedIndex(0);
    }

    private void cargarTablaHistorial(Long idDetalle) {
        ArrayList<AbonosVO> lista = (ArrayList<AbonosVO>) modeloAbonos.listarPorDetalle(idDetalle);
        tablaAbonosHelper.cargarTable(vista.tablaAbonos, lista);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscar && e.getKeyCode() == KeyEvent.VK_ENTER) {
            ejecutarBusqueda();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}

    private void abrirMenu() {
        if (usuario.getTipo().equals("Administrador")) {
            new ControladorMenuAdmin(new MenuAdmin(), usuario).iniciar();
        } else {
            new ControladorMenuCoordinador(new MenuCoordinador(), usuario).iniciar();
        }
        vista.dispose();
    }
    
    private void llenarMetodosPago() {
        vista.cbxPago.removeAllItems();
        vista.cbxPago.addItem("EFECTIVO");
        vista.cbxPago.addItem("DEPOSITO");
        vista.cbxPago.addItem("TARJETA");
    }
}