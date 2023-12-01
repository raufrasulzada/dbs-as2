package Methods;
import Connection.abstractConnection;
import AccessObjects.AccessBook;
import AccessObjects.AccessBookMoreDetailed;
import Entity.AuthorInfo;
import Entity.Book;
import Entity.BookMoreDetailed;
import Entity.BookOrderInfo;
import Entity.OrderInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BookMethods extends abstractConnection implements AccessBook, AccessBookMoreDetailed {
    private BookMoreDetailed getBookWithMoreDetails(ResultSet result) {
        try {
            int BookID = result.getInt("BookID");
            String title = result.getString("Title");
            String edition = result.getString("Edition");
            String publisher = result.getString("Publisher");
            int pages = result.getInt("Pages");
            int year = result.getInt("Year");
            double price = result.getDouble("Price");
            int booksLeft = result.getInt("BooksLeft");
    
            // Extract AuthorInfo fields from ResultSet
            int AuthorID = result.getInt("AuthorID");
            String AuthorName = result.getString("AuthorName");
            AuthorInfo author = (AuthorID != 0) ? new AuthorInfo(AuthorID, AuthorName) : null;
    
            int OrderID = result.getInt("OrderID");
            int CustomerID = result.getInt("CustomerID");
            int PlacedOrders = result.getInt("PlacedOrders");

            OrderInfo order = new OrderInfo(OrderID, CustomerID);
            BookOrderInfo bookorder = new BookOrderInfo(OrderID, BookID, PlacedOrders);

            // Create and return Book object with AuthorInfo
            return new BookMoreDetailed(BookID, title, edition, publisher, pages, year, price, booksLeft, author, order, bookorder);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }    

    @Override
    public boolean createBook(Book book) {
        try (Connection connection = establishConnection()) {
            String query = "INSERT INTO Book (BookID, Title, Edition, Publisher, Pages, Year, Price, BooksLeft) VALUES (?,?,?,?,?,?,?,?)";
            System.out.println("Query statement: " + query);

            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setInt(1, book.getBookID());
                pStatement.setString(2, book.getTitle());
                pStatement.setString(3, book.getEdition());
                pStatement.setString(4, book.getPublisher());
                pStatement.setInt(5, book.getPages());
                pStatement.setInt(6, book.getYear());
                pStatement.setDouble(7, book.getPrice());
                pStatement.setInt(8, book.getBooksLeft());

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
public List<Book> getAllBooks() {
    List<Book> books = new ArrayList<>();
    try (Connection connection = establishConnection()) {
        String query = "SELECT * FROM Book";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                int bookID = result.getInt("BookID");
                String title = result.getString("Title");
                String edition = result.getString("Edition");
                String publisher = result.getString("Publisher");
                int pages = result.getInt("Pages");
                int year = result.getInt("Year");
                double price = result.getDouble("Price");
                int booksLeft = result.getInt("BooksLeft");
                books.add(new Book(bookID, title, edition, publisher, pages, year, price, booksLeft));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("SQL Exception: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error: " + e.getMessage());
    }
    return books;
}


    @Override
    public boolean updateBook(Book book) {
        try (Connection connection = establishConnection()) {
            String query = "UPDATE Book SET Title=?, Edition=?, Publisher=?, Pages=?, Year=?, Price=?, BooksLeft=? WHERE BookID=?";
            System.out.println("Query statement: " + query);

            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setString(1, book.getTitle());
                pStatement.setString(2, book.getEdition());
                pStatement.setString(3, book.getPublisher());
                pStatement.setInt(4, book.getPages());
                pStatement.setInt(5, book.getYear());
                pStatement.setDouble(6, book.getPrice());
                pStatement.setInt(7, book.getBooksLeft());
                pStatement.setInt(8, book.getBookID());

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
    public boolean deleteBook(int BookID) {
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("DELETE FROM Book WHERE BookID = " + BookID);
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
        return true;
    }   

    @Override
    public Book getBook(int BookID) {
        try (Connection connection = establishConnection()) {
            String query = "SELECT * FROM Book WHERE BookID = ?";
            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setInt(1, BookID);
                ResultSet result = pStatement.executeQuery();
                if (result.next()) {
                    String title = result.getString("Title");
                    String edition = result.getString("Edition");
                    String publisher = result.getString("Publisher");
                    int pages = result.getInt("Pages");
                    int year = result.getInt("Year");
                    double price = result.getDouble("Price");
                    int booksLeft = result.getInt("BooksLeft");
                    return new Book(BookID, title, edition, publisher, pages, year, price, booksLeft);
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
    public List<BookMoreDetailed> retrieveAllBookInfo() {
        List<BookMoreDetailed> books = new ArrayList<>();
        try (Connection connection = establishConnection()) {
            String query = "SELECT * FROM Book JOIN BookAuthorInfo USING (BookID) JOIN AuthorInfo USING (AuthorID) JOIN BookOrderInfo USING (BookID) JOIN OrderInfo USING (OrderID)";
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute(query);
                ResultSet result = pStatement.getResultSet();
                while (result.next()) {
                    BookMoreDetailed book = getBookWithMoreDetails(result);
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return books;
    }
}