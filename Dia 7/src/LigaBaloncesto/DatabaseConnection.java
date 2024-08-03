package LigaBaloncesto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://bjewy0x6rprm9meqk6cm-mysql.services.clever-cloud.com:3306/bjewy0x6rprm9meqk6cm";
    private static final String USER = "uhu7cmxk2qqjawf9";
    private static final String PASSWORD = "rfnwOq3xig73Kd1G6RB2";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
