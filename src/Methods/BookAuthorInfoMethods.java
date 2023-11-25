package Methods;
import Connection.abstractConnection;
import AccessObjects.AccessBookAuthorInfo;
import Entity.BookAuthorInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BookAuthorInfoMethods extends abstractConnection implements AccessBookAuthorInfo {

    @Override
    public boolean createBookAuthorInfo(BookAuthorInfo bookAuthorInfo) {
        try (Connection connection = establishConnection()) {
            String query = "INSERT INTO BookAuthorInfo (AuthorID, BookID) VALUES (?, ?)";
            System.out.println("Query statement: " + query);

            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
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
    public List<BookAuthorInfo> getAllBookAuthorInfo() {
        List<BookAuthorInfo> bookAuthorInfos = new ArrayList<>();
        try (Connection connection = establishConnection()) {
            try (PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM BookAuthorInfo")) {
                ResultSet result = pStatement.executeQuery();
                while (result.next()) {
                    int AuthorID = result.getInt("AuthorID");
                    int BookID = result.getInt("BookID");
                    bookAuthorInfos.add(new BookAuthorInfo(AuthorID, BookID));
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
            try (PreparedStatement pStatement = connection.prepareStatement("UPDATE BookAuthorInfo SET BookID=? WHERE AuthorID=?")) {
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
    public boolean deleteBookAuthorInfo(int AuthorID, int BookID) {
        try (Connection connection = establishConnection()) {
            try (PreparedStatement pStatement = connection.prepareStatement("DELETE FROM BookAuthorInfo WHERE AuthorID = ? AND BookID = ?")) {
                pStatement.setInt(1, AuthorID);
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
    public List<Integer> getBookIDsByAuthor(int AuthorID) {
        List<Integer> bookIDs = new ArrayList<>();
        try (Connection connection = establishConnection()) {
            try (PreparedStatement pStatement = connection.prepareStatement("SELECT BookID FROM BookAuthorInfo WHERE AuthorID = ?")) {
                pStatement.setInt(1, AuthorID);
                ResultSet result = pStatement.executeQuery();
                while (result.next()) {
                    bookIDs.add(result.getInt("BookID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return bookIDs;
    }

    @Override
    public List<Integer> getAuthorIDsByBook(int BookID) {
        List<Integer> authorIDs = new ArrayList<>();
        try (Connection connection = establishConnection()) {
            try (PreparedStatement pStatement = connection.prepareStatement("SELECT AuthorID FROM BookAuthorInfo WHERE BookID = ?")) {
                pStatement.setInt(1, BookID);
                ResultSet result = pStatement.executeQuery();
                while (result.next()) {
                    authorIDs.add(result.getInt("AuthorID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return authorIDs;
    }
}
