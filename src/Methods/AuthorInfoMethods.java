package Methods;
import Connection.abstractConnection;
import AccessObjects.AccessAuthorInfo;
import Entity.AuthorInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AuthorInfoMethods extends abstractConnection implements AccessAuthorInfo {

    @Override
    public boolean createAuthorInfo(AuthorInfo author) {
        try (Connection connection = establishConnection()) {
            String query = "INSERT INTO AuthorInfo (AuthorID, AuthorName) VALUES (?,?)";
            System.out.println("Query statement: " + query);
            
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, author.getAuthorID());
            pStatement.setString(2, author.getAuthorName());
            int rowsAffected = pStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<AuthorInfo> getAllAuthors() {
        List<AuthorInfo> authors = new ArrayList<>();
        try (Connection connection = establishConnection()) {
            Statement pStatement = connection.createStatement();
            pStatement.execute("SELECT * FROM AuthorInfo");
            ResultSet result = pStatement.getResultSet();
            while (result.next()) {
                int AuthorID = result.getInt("AuthorID");
                String AuthorName = result.getString("AuthorName");
                authors.add(new AuthorInfo(AuthorID, AuthorName));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return authors;
    }

    @Override
    public boolean updateAuthorInfo(AuthorInfo author) {
        try (Connection connection = establishConnection()) {
            PreparedStatement pStatement = connection.prepareStatement("UPDATE AuthorInfo SET AuthorName=? WHERE AuthorID=?");
            pStatement.setString(1, author.getAuthorName());
            pStatement.setInt(2, author.getAuthorID());
            int rowsAffected = pStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAuthorInfo(int AuthorID) {
        try (Connection connection = establishConnection();) {
            Statement pStatement = connection.createStatement();
            pStatement.execute("DELETE FROM AuthorInfo WHERE AuthorID = " + AuthorID);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public AuthorInfo getAuthorById(int ID) {
        AuthorInfo author = null;
        try (Connection connection = establishConnection()) {
            Statement pStatement = connection.createStatement();
            pStatement.execute("SELECT * FROM AuthorInfo WHERE AuthorID = " + ID);
            ResultSet result = pStatement.getResultSet();
            while (result.next()) {
                int AuthorID = result.getInt("AuthorID");
                String AuthorName = result.getString("AuthorName");
                author = new AuthorInfo(AuthorID, AuthorName);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return author;
    }
}