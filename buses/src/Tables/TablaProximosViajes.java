/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tables;

import ClassVO.ClienteVO;
import ClassVO.ViajeVO;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author alxyg
 */
public class TablaProximosViajes {
    public void cargarTabla(JTable tabla, ArrayList<ViajeVO> list) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dt.addColumn("Id");
        dt.addColumn("Destino");
        dt.addColumn("Fecha");
        dt.addColumn("lugares disponibles");

        ViajeVO viaje = new ViajeVO();

        for (int i = 0; i < list.size(); i++) {
            Object fila[] = new Object[4];
            viaje = list.get(i);
            fila[0] = viaje.getId();
            fila[1] = viaje.getNombreDestino();
            fila[2] = viaje.getFecha();
            fila[3] = viaje.getNoAsientos();
            dt.addRow(fila);
        }
        tabla.setModel(dt);
        tabla.setRowHeight(60);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(190);
        columnModel.getColumn(2).setPreferredWidth(70);
        columnModel.getColumn(2).setPreferredWidth(70);
    }
}
