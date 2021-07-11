/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import ClassVO.HotelVO;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author alanm
 */
public class TablaHotelDestinoViaje {
     public void cargarTabla(JTable tabla, ArrayList<Object[]> list) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dt.addColumn("Id");
        dt.addColumn("Hotel");
        dt.addColumn("Habitaciones");
        dt.addColumn("Disponibles");
        dt.addColumn("idH");
        dt.addColumn("Reg");


        Object[] hotel;

        for (int i = 0; i < list.size(); i++) {
            hotel = list.get(i);
            dt.addRow(hotel);
        }
        tabla.setModel(dt);
        tabla.setRowHeight(60);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(210);

    }

    public void cargarTablaVacia(JTable tabla) {
         tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dt.addColumn("Id");
        dt.addColumn("Hotel");
        dt.addColumn("Habitaciones");
        dt.addColumn("Disponibles");
        dt.addColumn("idH");
        dt.addColumn("Reg");
        
        tabla.setModel(dt);
        tabla.setRowHeight(60);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(210);
    }
}
