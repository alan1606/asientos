/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tables;

import ClassVO.AbonosVO;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author alxyg
 */
public class TablaAbonos {
    public void cargarTable(JTable tabla, ArrayList<AbonosVO> list){
    
        // Se utiliza el Render para centrar o dar formato a las celdas (basado en tu código de Clientes)
        tabla.setDefaultRenderer(Object.class, new Render());
        //cargaCabecerasYColoresDeLaTabla(tabla);
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Definimos las columnas que queremos mostrar al usuario
        dt.addColumn("ID Abono");
        dt.addColumn("Folio Venta");
        dt.addColumn("Monto");
        dt.addColumn("Método de Pago");
        dt.addColumn("Fecha Aplicado");
        dt.addColumn("Usuario");
        

        // Llenamos las filas con la información de la lista
        for (int i = 0; i < list.size(); i++) {
            Object fila[] = new Object[6];
            AbonosVO abono = list.get(i);
            
            fila[0] = abono.getId();
            fila[1] = abono.getIdDetalle();
            fila[2] = "$ " + abono.getMonto();
            fila[3] = abono.getMetodoPagoDescripcion();
            fila[4] = abono.getFechaAbono();
            fila[5] = abono.getNombreUsuario();
            
            dt.addRow(fila);
        }

        tabla.setModel(dt);
        tabla.setRowHeight(40); // Ajustamos un poco la altura para que sea legible

        // Configuración del ancho de las columnas
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // ID
        columnModel.getColumn(1).setPreferredWidth(100); // Monto
        columnModel.getColumn(2).setPreferredWidth(120); // Método
        columnModel.getColumn(3).setPreferredWidth(150); // Fecha
        columnModel.getColumn(4).setPreferredWidth(80);  // Usuario
        
    }
    private static void cargaCabecerasYColoresDeLaTabla(JTable tabla){
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   
        // --- AQUÍ CAMBIAS EL COLOR DE LA FILA SELECCIONADA ---
        tabla.setSelectionBackground(new Color(184, 207, 229)); // Un azul suave
        tabla.setSelectionForeground(Color.DARK_GRAY);         // Texto oscuro al seleccionar

        // Eliminar las líneas de la cuadrícula si quieres un look más limpio (opcional)
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(new Color(230, 230, 230));
        tabla.getTableHeader().setReorderingAllowed(false); // Bloquea el mover columnas
        tabla.getTableHeader().setBackground(new Color(45, 45, 45)); // Fondo oscuro
        tabla.getTableHeader().setForeground(Color.WHITE);           // Texto blanco
    }
}
