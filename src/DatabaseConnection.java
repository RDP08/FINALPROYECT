import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mysql://db4free.net:3306/almacenitlafinal?useSSL=false";
    private static final String DATABASE_USER = "estuditlafinal";
    private static final String DATABASE_PASSWORD = "itla123.";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}


