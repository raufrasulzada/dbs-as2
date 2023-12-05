package Methods;
import Connection.abstractConnection;
import AccessObjects.AccessBookAuthorInfo;
import Entity.AuthorInfo;
import Entity.BookAuthorInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BookAuthorInfoMethods extends abstractConnection implements AccessBookAuthorInfo {

    @Override
    public boolean createBookAuthorInfo(BookAuthorInfo bookAuthorInfo) {
        try (Connection connection = establishConnection()) {
            String query = "INSERT INTO BookAuthorInfo (BookID, AuthorID) VALUES (?, ?)";
            System.out.println("Query statement: " + query);

            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setInt(1, bookAuthorInfo.getBookID());
                pStatement.setInt(2, bookAuthorInfo.getAuthorID());
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
    public List<BookAuthorInfo> getAllBookAuthorInfo() {
        List<BookAuthorInfo> bookAuthorInfos = new ArrayList<>();
        try (Connection connection = establishConnection()) {
            try (PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM BookAuthorInfo")) {
                ResultSet result = pStatement.executeQuery();
                while (result.next()) {
                    int BookID = result.getInt("BookID");
                    int AuthorID = result.getInt("AuthorID");
                    bookAuthorInfos.add(new BookAuthorInfo(BookID, AuthorID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return bookAuthorInfos;
    }

    @Override
    public boolean updateBookAuthorInfo(BookAuthorInfo bookAuthorInfo) {
        try (Connection connection = establishConnection()) {
            try (PreparedStatement pStatement = connection.prepareStatement("UPDATE BookAuthorInfo SET AuthorID=? WHERE BookID=?")) {
                pStatement.setInt(1, bookAuthorInfo.getAuthorID());
                pStatement.setInt(2, bookAuthorInfo.getBookID());
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
    public boolean deleteBookAuthorInfo(int BookID, int AuthorID) {
        try (Connection connection = establishConnection()) {
            try (PreparedStatement pStatement = connection.prepareStatement("DELETE FROM BookAuthorInfo WHERE BookID = ? AND AuthorID = ?")) {
                pStatement.setInt(1, BookID);
                pStatement.setInt(2, AuthorID);
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
    public BookAuthorInfo getBookAuthorInfoByBookID(int ID) {
        BookAuthorInfo bookauthorinf = null;
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("SELECT * FROM BookAuthorInfo WHERE BookID = " + ID);
                ResultSet result = pStatement.getResultSet();
                while (result.next()) {
                    int BookID = result.getInt("BookID");
                    int AuthorID = result.getInt("AuthorID");
                    bookauthorinf = new BookAuthorInfo(AuthorID, BookID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return bookauthorinf;
    }
}
