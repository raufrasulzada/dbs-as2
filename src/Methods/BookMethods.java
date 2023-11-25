package Methods;
import Connection.abstractConnection;
import AccessObjects.AccessBook;
import Entity.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BookMethods extends abstractConnection implements AccessBook {

    @Override
    public boolean createBook(Book book) {
        try (Connection connection = establishConnection()) {
            String query = "INSERT INTO Book (BookID, Title, AuthorID, Edition, Publisher, Pages, Year, Price, BooksLeft) VALUES (?,?,?,?,?,?,?,?,?)";
            System.out.println("Query statement: " + query);

            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setInt(1, book.getBookID());
                pStatement.setString(2, book.getTitle());
                pStatement.setInt(3, book.getAuthorID());
                pStatement.setString(4, book.getEdition());
                pStatement.setString(5, book.getPublisher());
                pStatement.setInt(6, book.getPages());
                pStatement.setInt(7, book.getYear());
                pStatement.setDouble(8, book.getPrice());
                pStatement.setInt(9, book.getBooksLeft());

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
        String query = "Not ready yet...";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                // ?
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
            String query = "UPDATE Book SET Title=?, AuthorID=?, Edition=?, Publisher=?, Pages=?, Year=?, Price=?, BooksLeft=? WHERE BookID=?";
            System.out.println("Query statement: " + query);

            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setString(1, book.getTitle());
                pStatement.setInt(2, book.getAuthorID());
                pStatement.setString(3, book.getEdition());
                pStatement.setString(4, book.getPublisher());
                pStatement.setInt(5, book.getPages());
                pStatement.setInt(6, book.getYear());
                pStatement.setDouble(7, book.getPrice());
                pStatement.setInt(8, book.getBooksLeft());
                pStatement.setInt(9, book.getBookID());

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
    public Book getBookByID(int BookID) {
        Book book = null;
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("SELECT * FROM Book WHERE BookID = " + BookID);
                ResultSet result = pStatement.getResultSet();
                while (result.next()) {
                    int bookID = result.getInt("BookID");
                    String title = result.getString("Title");
                    int authorID = result.getInt("AuthorID");
                    String edition = result.getString("Edition");
                    String publisher = result.getString("Publisher");
                    int pages = result.getInt("Pages");
                    int year = result.getInt("Year");
                    double price = result.getDouble("Price");
                    int booksLeft = result.getInt("BooksLeft");
                    book = new Book(bookID, title, authorID, edition, publisher, pages, year, price, booksLeft);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return book;
    }
}
