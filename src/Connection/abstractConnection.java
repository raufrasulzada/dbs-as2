package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class abstractConnection {
    public static Connection establishConnection() {
        String url = "jdbc:postgresql://localhost/Assignment2";
        String userName = "postgres";
        String password = "rauf1234";
        Connection establishConnection = null;
        try {
            establishConnection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return establishConnection;
    }
}