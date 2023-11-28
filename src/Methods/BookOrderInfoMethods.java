package Methods;
import Connection.abstractConnection;
import AccessObjects.AccessBookOrderInfo;
import Entity.Book;
import Entity.BookOrderInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BookOrderInfoMethods extends abstractConnection implements AccessBookOrderInfo {

    @Override
public boolean createBookOrderInfo(BookOrderInfo bookOrder) {
    try (Connection connection = establishConnection();
         PreparedStatement selectBookStatement = connection.prepareStatement("SELECT BooksLeft FROM Book WHERE BookID = ?");
         PreparedStatement insertOrderStatement = connection.prepareStatement("INSERT INTO BookOrderInfo (OrderID, BookID, PlacedOrders) VALUES (?, ?, ?)");
         PreparedStatement updateBookStatement = connection.prepareStatement("UPDATE Book SET BooksLeft = ? WHERE BookID = ?")) {

        connection.setAutoCommit(false);

        int orderId = bookOrder.getOrderID();
        int bookId = bookOrder.getBookID();
        int placedOrders = bookOrder.getPlacedOrders();

        selectBookStatement.setInt(1, bookId);
        ResultSet bookResult = selectBookStatement.executeQuery();
        int booksLeft = 0;

        if (bookResult.next()) {
            booksLeft = bookResult.getInt("BooksLeft");
        }

        if (booksLeft >= placedOrders) {
            insertOrderStatement.setInt(1, orderId);
            insertOrderStatement.setInt(2, bookId);
            insertOrderStatement.setInt(3, placedOrders);
            insertOrderStatement.executeUpdate();

            int updatedBooksLeft = booksLeft - placedOrders;
            updateBookStatement.setInt(1, updatedBooksLeft);
            updateBookStatement.setInt(2, bookId);
            updateBookStatement.executeUpdate();
            connection.commit();
            return true;
        } else {
            connection.rollback();
            System.out.println("Not enough books.");
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("SQL Exception: " + e.getMessage());
        return false;
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}
       

    @Override
    public List<BookOrderInfo> getAllBookOrders() {
        List<BookOrderInfo> bookOrders = new ArrayList<>();
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("SELECT * FROM BookOrderInfo");
                ResultSet result = pStatement.getResultSet();
                while (result.next()) {
                    int OrderID = result.getInt("OrderID");
                    int BookID = result.getInt("BookID");
                    int PlacedOrders = result.getInt("PlacedOrders");
                    bookOrders.add(new BookOrderInfo(OrderID, BookID, PlacedOrders));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return bookOrders;
    }

    @Override
    public boolean deleteBookOrderInfo(int OrderID, int BookID) {
        try (Connection connection = establishConnection()) {
            String query = "DELETE FROM BookOrderInfo WHERE OrderID = ? AND BookID = ?";
            System.out.println("Query statement: " + query);

            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setInt(1, OrderID);
                pStatement.setInt(2, BookID);
                int rowsAffected = pStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public BookOrderInfo getBookOrderByOrderID(int OrderID) {
        BookOrderInfo bookOrder = null;
        try (Connection connection = establishConnection()) {
            String query = "SELECT * FROM BookOrderInfo WHERE OrderID = ?";
            System.out.println("Query statement: " + query);

            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setInt(1, OrderID);
                ResultSet result = pStatement.executeQuery();
                if (result.next()) {
                    int BookID = result.getInt("BookID");
                    int PlacedOrders = result.getInt("PlacedOrders");
                    bookOrder = new BookOrderInfo(OrderID, BookID, PlacedOrders);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return bookOrder;
    }
}
