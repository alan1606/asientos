package Conexion;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/asientos?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    private static Connection singleConnection;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            singleConnection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return singleConnection;
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static java.sql.Date getFechaActual() {
        //Obtener fecha actual y guardar en String 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendario = Calendar.getInstance();
        java.util.Date fechaT = (java.util.Date) calendario.getTime();
        String fecha = dateFormat.format(fechaT);
        try {
            fechaT = (Date) dateFormat.parse(fecha);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        java.sql.Date fechaFinal = new java.sql.Date(fechaT.getTime());
        return fechaFinal;
    }

    public static java.sql.Date aSqlDate(java.util.Date miFecha) {
        return new java.sql.Date(miFecha.getTime());
    }

    public static java.util.Date stringADate(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(fecha);
    }

    public static java.util.Date sumarDias(java.util.Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias); // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

}
