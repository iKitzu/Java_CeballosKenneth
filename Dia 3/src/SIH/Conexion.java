package SIH;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class Conexion {
    private Connection con;

    public Conexion() {
        new Scanner(System.in);
    }

    public Connection establecerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://bx9c6cxzdvyukiazsr2r-mysql.services.clever-cloud.com:3306/bx9c6cxzdvyukiazsr2r",
                "uoe0m6oyjx8tqnby",
                "Lh5X4tT1FbbiEwZmiG51"
            );
            //JOptionPane.showMessageDialog(null, "La conexión es un éxito :)");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error en la conexión :(, error:" + e);
            JOptionPane.showMessageDialog(null, "Error en la conexión: " + e.toString());
        }
        return con;
    }
}