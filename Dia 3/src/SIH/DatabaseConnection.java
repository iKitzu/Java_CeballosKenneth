package SIH;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://bx9c6cxzdvyukiazsr2r-mysql.services.clever-cloud.com:3306/bx9c6cxzdvyukiazsr2r";
    private static final String USER = "uoe0m6oyjx8tqnby";
    private static final String PASSWORD = "Lh5X4tT1FbbiEwZmiG51";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
