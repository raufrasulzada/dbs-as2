import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class manageDB {
    
    public static void main(String[] args) throws Exception {
        try {
            String url = "jdbc:postgresql://localhost:5432/Assignment2";
            String userName = "postgres";
            String password = ""; // Empty for confidentiality.
    
            Connection connection = DriverManager.getConnection(url, userName, password);
    
            // This is the line to insert any query lines for our database.
            String insertQuery = "";

            Statement statement = connection.createStatement();
            statement.executeUpdate(insertQuery);
    
            // Close resources
            statement.close();
            connection.close();
    
            // To show message as a JPanel:
            JOptionPane.showMessageDialog(null, "Values inserted into 'book' table successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
