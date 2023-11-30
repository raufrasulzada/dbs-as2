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

    // Since BookMethods is an abstract class, I could not directly instantiate it. That is why I created ConcreteBookMethods and then instantiate that concrete class
    public class ConcreteBookMethods extends BookMethods {
    }

    @Override
    public boolean updateBookOrderInfo(BookOrderInfo bookOrder) {
        try (Connection connection = establishConnection();
             PreparedStatement updateStatement = connection.prepareStatement("UPDATE BookOrderInfo SET PlacedOrders = ? WHERE OrderID = ? AND BookID = ?");
             PreparedStatement updateBooksLeftStatement = connection.prepareStatement("UPDATE Book SET BooksLeft = BooksLeft - ? WHERE BookID = ?")) {
                // Retrieve the existing PlacedOrders for the book order
            int existingPlacedOrders = getBookOrderInfo(bookOrder.getOrderID(), bookOrder.getBookID()).getPlacedOrders();
    
            // Calculate the difference in PlacedOrders
            int ordersDifference = bookOrder.getPlacedOrders() - existingPlacedOrders;
    
            // Update PlacedOrders in BookOrderInfo
            updateStatement.setInt(1, bookOrder.getPlacedOrders());
            updateStatement.setInt(2, bookOrder.getOrderID());
            updateStatement.setInt(3, bookOrder.getBookID());
            int rowsAffected = updateStatement.executeUpdate();
    
            // Use ConcreteBookMethods to get BooksLeft
            ConcreteBookMethods concreteBookMethods = new ConcreteBookMethods();
            int updatedBooksLeft = concreteBookMethods.getBook(bookOrder.getBookID()).getBooksLeft() - ordersDifference;
    
            // Check if BooksLeft will go below zero
            if (updatedBooksLeft < 0) {
                // If it will go below zero, throw an exception or handle the situation accordingly
                throw new IllegalArgumentException("Updating will result in negative BooksLeft.");
            }
    
            // Update BooksLeft only if it won't go below zero
            updateBooksLeftStatement.setInt(1, ordersDifference);
            updateBooksLeftStatement.setInt(2, bookOrder.getBookID());
            updateBooksLeftStatement.executeUpdate();
    
            return rowsAffected > 0;
    
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
public boolean deleteBookOrderInfo(int OrderID, int BookID) {
    try (Connection connection = establishConnection()) {
        // Retrieve placed orders before deletion
        BookOrderInfo bookOrderBeforeDeletion = getBookOrderInfo(OrderID, BookID);
        if (bookOrderBeforeDeletion == null) {
            System.out.println("BookOrderInfo not found for OrderID: " + OrderID + " and BookID: " + BookID);
            return false;
        }
        int placedOrders = bookOrderBeforeDeletion.getPlacedOrders();
        String deleteQuery = "DELETE FROM BookOrderInfo WHERE OrderID = ? AND BookID = ?";
        try (PreparedStatement pStatement = connection.prepareStatement(deleteQuery)) {
            pStatement.setInt(1, OrderID);
            pStatement.setInt(2, BookID);
            int rowsAffected = pStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Update BooksLeft in the Books table
                String updateBooksQuery = "UPDATE Book SET BooksLeft = BooksLeft + ? WHERE BookID = ?";
                try (PreparedStatement updateBooksStatement = connection.prepareStatement(updateBooksQuery)) {
                    updateBooksStatement.setInt(1, placedOrders);
                    updateBooksStatement.setInt(2, BookID);
                    updateBooksStatement.executeUpdate();
                }

                return true;
            } else {
                System.out.println("Deletion failed. No rows affected.");
                return false;
            }
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

    public BookOrderInfo getBookOrderInfo(int OrderID, int BookID) {
        try (Connection connection = establishConnection()) {
            String query = "SELECT * FROM BookOrderInfo WHERE OrderID = ? AND BookID = ?";
            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setInt(1, OrderID);
                pStatement.setInt(2, BookID);
                ResultSet result = pStatement.executeQuery();
                if (result.next()) {
                    int PlacedOrders = result.getInt("PlacedOrders");
                    return new BookOrderInfo(OrderID, BookID, PlacedOrders);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return null;
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
