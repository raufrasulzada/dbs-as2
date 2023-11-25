package Methods;
import Connection.abstractConnection;
import AccessObjects.AccessCustomerInfo;
import Entity.CustomerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class CustomerInfoMethods extends abstractConnection implements AccessCustomerInfo {

    @Override
    public boolean createCustomerInfo(CustomerInfo customer) {
        try (Connection connection = establishConnection()) {
            String query = "INSERT INTO CustomerInfo (CustomerID, CustomerName) VALUES (?,?)";
            System.out.println("Query statement: " + query);

            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setInt(1, customer.getCustomerID());
                pStatement.setString(2, customer.getCustomerName());
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
    public List<CustomerInfo> getAllCustomers() {
        List<CustomerInfo> customers = new ArrayList<>();
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("SELECT * FROM CustomerInfo");
                ResultSet result = pStatement.getResultSet();
                while (result.next()) {
                    int CustomerID = result.getInt("CustomerID");
                    String CustomerName = result.getString("CustomerName");
                    customers.add(new CustomerInfo(CustomerID, CustomerName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return customers;
    }

    @Override
    public boolean updateCustomerInfo(CustomerInfo customer) {
        try (Connection connection = establishConnection()) {
            try (PreparedStatement pStatement = connection.prepareStatement("UPDATE CustomerInfo SET CustomerName=? WHERE CustomerID=?")) {
                pStatement.setString(1, customer.getCustomerName());
                pStatement.setInt(2, customer.getCustomerID());
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
    public boolean deleteCustomerInfo(int CustomerID) {
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("DELETE FROM CustomerInfo WHERE CustomerID = " + CustomerID);
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
    public CustomerInfo getCustomerById(int ID) {
        CustomerInfo customer = null;
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("SELECT * FROM CustomerInfo WHERE CustomerID = " + ID);
                ResultSet result = pStatement.getResultSet();
                while (result.next()) {
                    int CustomerID = result.getInt("CustomerID");
                    String CustomerName = result.getString("CustomerName");
                    customer = new CustomerInfo(CustomerID, CustomerName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return customer;
    }
}
