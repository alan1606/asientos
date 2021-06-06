package Tables;



import ClassVO.HotelVO;
import ClassVO.ViajeVO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import javax.swing.table.TableColumnModel;

public class TablaHoteles {



    public void cargarTabla(JTable tabla, ArrayList<HotelVO> list) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dt.addColumn("Id");
        dt.addColumn("Nombre");



        HotelVO hotel = new HotelVO();

        for (int i = 0; i < list.size(); i++) {
            Object fila[] = new Object[2];
            hotel = list.get(i);
            fila[0] = hotel.getId();
            fila[1] = hotel.getNombre();
            dt.addRow(fila);
        }
        tabla.setModel(dt);
        tabla.setRowHeight(60);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(190);

    }

   
}
