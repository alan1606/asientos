package Tables;



import ClassVO.UsuarioVO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import javax.swing.table.TableColumnModel;

public class TablaUsuarios {



    public void cargarTabla(JTable tabla, ArrayList<UsuarioVO> list) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dt.addColumn("Id");
        dt.addColumn("Usuario");
        dt.addColumn("Nombre");
        dt.addColumn("Tipo");


        UsuarioVO usuario = new UsuarioVO();

        for (int i = 0; i < list.size(); i++) {
            Object fila[] = new Object[4];
            usuario = list.get(i);
            fila[0] = usuario.getId();
            fila[1] = usuario.getUsuario();
            fila[2] = usuario.getNombre();
            fila[3] = usuario.getTipo();
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
