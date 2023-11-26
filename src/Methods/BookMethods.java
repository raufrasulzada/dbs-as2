package Methods;
import Connection.abstractConnection;
import AccessObjects.AccessBook;
import Entity.AuthorInfo;
import Entity.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BookMethods extends abstractConnection implements AccessBook {
    private Book getBook(ResultSet result) {
        try {
        int BookID = result.getInt("BookID");
        String title = result.getString("Title");
        String edition = result.getString("Edition");
        String publisher = result.getString("Publisher");
        int pages = result.getInt("Pages");
        int year = result.getInt("Year");
        double price = result.getDouble("Price");
        int booksLeft = result.getInt("BooksLeft");
        String AuthorName = result.getString("AuthorName");
        int AuthorID = result.getInt("AuthorID");
        AuthorInfo author = new AuthorInfo(AuthorID, AuthorName);
        return new Book(BookID, title, edition, publisher, pages, year, price, booksLeft, author);
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
    public Book getBookByID(int BookID) {
        Book book = null;
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("SELECT * FROM Book WHERE BookID = " + BookID);
                ResultSet result = pStatement.getResultSet();
                while (result.next()) {
                    int bookID = result.getInt("BookID");
                    String title = result.getString("Title");
                    String edition = result.getString("Edition");
                    String publisher = result.getString("Publisher");
                    int pages = result.getInt("Pages");
                    int year = result.getInt("Year");
                    double price = result.getDouble("Price");
                    int booksLeft = result.getInt("BooksLeft");
                    book = new Book(bookID, title, edition, publisher, pages, year, price, booksLeft);
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

    @Override
    public List<Book> retrieveAllInformation() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = establishConnection()) {
            String query = "SELECT * FROM (((Book NATURAL JOIN BookAuthorInfo) JOIN AuthorInfo USING(AuthorID)) LEFT JOIN BookOrderInfo USING(BookID)) LEFT JOIN OrderInfo USING(OrderID)";
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute(query);
                ResultSet result = pStatement.getResultSet();
                while (result.next()) {
                    Book book = getBook(result);
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