import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class manageDB {
    
    private static final String url = "jdbc:postgresql://localhost:5432/Assignment2";
    private static final String userName = "postgres";
    private static final String password = ""; // Empty for confidentiality.

    public static void main(String[] args) throws Exception {
        try {
    
            Connection connection = DriverManager.getConnection(url, userName, password);
            JOptionPane.showMessageDialog(null, "Connection established", "System Message", JOptionPane.INFORMATION_MESSAGE);
    
            // insertAuthorInfo();
            // insertCustomerInfo();
            // insertBook();
            // retrieveAllBookInformation();
            // removeRecord();

        } catch (SQLException e) {
            handleSQLException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertAuthorInfo(Connection connection, int authorid, String authorname) throws SQLException{
        String insertQuery = "INSERT INTO AuthorInfo (authorid, authorname) " + "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, authorid);
            preparedStatement.setString(2, authorname);
            preparedStatement.executeUpdate();
        }
        JOptionPane.showMessageDialog(null, "CRUD operations executed successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void insertOrderInfo(Connection connection, int orderid, int customerid, int placedorders) throws SQLException {
        String insertQuery = "INSERT INTO OrderInfo (orderid, customerid, placedorders)" + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, orderid);
            preparedStatement.setInt(2, customerid);
            preparedStatement.setInt(3, placedorders);
            preparedStatement.executeUpdate();
        }
        JOptionPane.showMessageDialog(null, "CRUD operations executed successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void insertCustomerInfo(Connection connection, int customerid, String customername) throws SQLException {
        String insertQuery = "INSERT INTO OrderInfo (orderid, customerid, placedorders)" + "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, customerid);
            preparedStatement.setString(2, customername);
            preparedStatement.executeUpdate();
        }
        JOptionPane.showMessageDialog(null, "CRUD operations executed successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void insertBook(Connection connection, String ISBN, String title, int authorid, String edition, String publisher, int pages, int year, double price, int booksleft) throws SQLException{
        String insertQuery = "INSERT INTO book (ISBN, title, authorid, edition, publisher, pages, year, price, booksleft) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, ISBN);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, authorid);
            preparedStatement.setString(4, edition);
            preparedStatement.setString(5, publisher);
            preparedStatement.setInt(6, pages);
            preparedStatement.setInt(7, year);
            preparedStatement.setDouble(8, price);
            preparedStatement.setInt(9, booksleft);
            preparedStatement.executeUpdate();
        }
         JOptionPane.showMessageDialog(null, "CRUD operations executed successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
    }

public static void retrieveAllBookInformation(Connection connection) {
        String selectQuery = "SELECT b.ISBN, b.Title, a.AuthorName, b.Edition, b.Publisher, b.Pages, b.Year, b.Price, b.BooksLeft, o.OrderID, o.CustomerID, o.PlacedOrders, o.TotalPrice "
                + "FROM Book b "
                + "JOIN AuthorInfo a ON b.AuthorID = a.AuthorID "
                + "LEFT JOIN OrderInfo o ON b.ISBN = o.ISBN";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String ISBN = resultSet.getString("ISBN");
                String title = resultSet.getString("Title");
                String authorName = resultSet.getString("AuthorName");
                String edition = resultSet.getString("Edition");
                String publisher = resultSet.getString("Publisher");
                int pages = resultSet.getInt("Pages");
                int year = resultSet.getInt("Year");
                double price = resultSet.getDouble("Price");
                int booksLeft = resultSet.getInt("BooksLeft");

                int orderID = resultSet.getInt("OrderID");
                int customerID = resultSet.getInt("CustomerID");
                int placedOrders = resultSet.getInt("PlacedOrders");
                double totalPrice = resultSet.getDouble("TotalPrice");

                System.out.println("ISBN: " + ISBN + ", Title: " + title + ", Author: " + authorName + ", Edition: " + edition
                        + ", Publisher: " + publisher + ", Pages: " + pages + ", Year: " + year + ", Price: " + price
                        + ", Books Left: " + booksLeft + ", OrderID: " + orderID + ", CustomerID: " + customerID
                        + ", Placed Orders: " + placedOrders + ", Total Price: " + totalPrice);
            }
            JOptionPane.showMessageDialog(null, "CRUD operations executed successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static void updateBookDetails(Connection connection, String ISBN, String newTitle, String newEdition, double newPrice, int newBooksLeft) {
        String updateQuery = "UPDATE Book SET Title = ?, Edition = ?, Price = ?, BooksLeft = ? WHERE ISBN = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newTitle);
            preparedStatement.setString(2, newEdition);
            preparedStatement.setDouble(3, newPrice);
            preparedStatement.setInt(4, newBooksLeft);
            preparedStatement.setString(5, ISBN);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Book details are updated successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No records found for update", "Error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "CRUD operations executed successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static <x> void removeRecord(Connection connection, String tablename, String columnName, x thevalue) throws SQLException {
        String deleteQuery = "DELETE FROM " + tablename + " WHERE " + columnName + " = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
            deleteStatement.setObject(1, thevalue);

            int affectedRows = deleteStatement.executeUpdate();
            if (affectedRows > 0) {
             JOptionPane.showMessageDialog(null, "CRUD operations executed successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
            JOptionPane.showMessageDialog(null, "CRUD operations failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void handleSQLException(SQLException e) {
        String errorMessage;
        if (e.getSQLState().equals("23505")) {
            errorMessage = "The record already exists.";
        } else {
            errorMessage = "Error executing SQL query: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}