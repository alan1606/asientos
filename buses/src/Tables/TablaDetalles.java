/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tables;

import ClassVO.DetalleVO;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author alxyg
 */
public class TablaDetalles {
    public void cargarTablaDetalleVistaAbonos(JTable tabla, ArrayList<DetalleVO> list){
    
        tabla.setDefaultRenderer(Object.class, new Render());
        
        DefaultTableModel dt = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }  
        };
        // Definición de columnas basadas en tu lógica de negocio
        dt.addColumn("Folio");      // id
        dt.addColumn("Fecha");      // fecha_venta
        dt.addColumn("Destino");    // ciudad (Join)
        dt.addColumn("Cliente");    // nombre (Join)
        dt.addColumn("Pax");        // personas
        dt.addColumn("Regreso");    // fecha_regreso
        dt.addColumn("Costo");      // costo
        dt.addColumn("Anticipo");   // anticipo
        dt.addColumn("Vendedor");   // nombre usuario (Join)

        // Llenado de la tabla
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[9];
                DetalleVO vo = list.get(i);

                fila[0] = vo.getId();
                fila[1] = vo.getFechaVenta();
                fila[2] = vo.getCiudad();         // Campo extra en VO
                fila[3] = vo.getNombreCliente();  // Campo extra en VO
                fila[4] = vo.getPersonas();
                fila[5] = vo.getFechaRegreso();
                fila[6] = vo.getCosto();
                fila[7] = vo.getAnticipo();
                fila[8] = vo.getNombreVendedor(); // Campo extra en VO

                dt.addRow(fila);
            }
        }

        tabla.setModel(dt);
        tabla.setRowHeight(45); // Altura cómoda para visualización

        // Configuración de anchos de columna para optimizar espacio
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // Folio
        columnModel.getColumn(1).setPreferredWidth(90);  // Fecha
        columnModel.getColumn(2).setPreferredWidth(130); // Destino
        columnModel.getColumn(3).setPreferredWidth(180); // Cliente
        columnModel.getColumn(4).setPreferredWidth(40);  // Pax
        columnModel.getColumn(5).setPreferredWidth(90);  // Regreso
        columnModel.getColumn(6).setPreferredWidth(80);  // Costo
        columnModel.getColumn(7).setPreferredWidth(80);  // Anticipo
        columnModel.getColumn(8).setPreferredWidth(100); // Vendedor
    }
}
