package Tables;

import ClassVO.ClienteVO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import javax.swing.table.TableColumnModel;

public class TablaClientes {



    public void cargarTabla(JTable tabla, ArrayList<ClienteVO> list) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dt.addColumn("Id");
        dt.addColumn("Nombre");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");
        dt.addColumn("Tipo");

        ClienteVO cliente = new ClienteVO();

        for (int i = 0; i < list.size(); i++) {
            Object fila[] = new Object[5];
            cliente = list.get(i);
            fila[0] = cliente.getId();
            fila[1] = cliente.getNombre();
            fila[2] = cliente.getTelefono();
            fila[3] = cliente.getCorreo();
            fila[4] = cliente.getTipo();
            dt.addRow(fila);
        }
        tabla.setModel(dt);
        tabla.setRowHeight(60);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(190);
        columnModel.getColumn(2).setPreferredWidth(70);
        columnModel.getColumn(3).setPreferredWidth(130);
        columnModel.getColumn(4).setPreferredWidth(60);
    }

   
    /* public static int cargarPaquetes(JTable tabla, String descripcion) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dt.addColumn("Id");
        dt.addColumn("Descripcion");
        dt.addColumn("Precio");

        PaqueteVO paquete = new PaqueteVO();
        ArrayList<PaqueteVO> list = PaqueteDAO.encontrar(descripcion);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[3];
                paquete = list.get(i);
                fila[0] = paquete.getId();
                fila[1] = paquete.getDescripcion();
                fila[2] = paquete.getPrecio();
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(60);
            TableColumnModel columnModel = tabla.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(30);
            columnModel.getColumn(1).setPreferredWidth(190);
            columnModel.getColumn(2).setPreferredWidth(50);
        } else {
            Object fila[] = new Object[3];
            fila[0] = "";
            fila[1] = "";
            fila[2] = "";
            dt.addRow(fila);
            tabla.setModel(dt);
            tabla.setRowHeight(60);
            TableColumnModel columnModel = tabla.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(30);
            columnModel.getColumn(1).setPreferredWidth(190);
            columnModel.getColumn(2).setPreferredWidth(50);
        }
        return list.size();
    }*/
}
