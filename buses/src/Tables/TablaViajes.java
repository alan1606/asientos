package Tables;



import ClassVO.ViajeVO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import javax.swing.table.TableColumnModel;

public class TablaViajes {



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
        dt.addColumn("Asientos");
        dt.addColumn("Observaciones");

        ViajeVO viaje = new ViajeVO();

        for (int i = 0; i < list.size(); i++) {
            Object fila[] = new Object[5];
            viaje = list.get(i);
            fila[0] = viaje.getId();
            fila[1] = viaje.getIdDestino();
            fila[2] = viaje.getFecha();
            fila[3] = viaje.getNoAsientos();
            fila[4] = viaje.getObservaciones();
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
