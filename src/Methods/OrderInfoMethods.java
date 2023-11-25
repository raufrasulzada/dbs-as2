package Methods;
import Connection.abstractConnection;
import AccessObjects.AccessOrderInfo;
import Entity.OrderInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class OrderInfoMethods extends abstractConnection implements AccessOrderInfo {

    @Override
    public boolean createOrderInfo(OrderInfo order) {
        try (Connection connection = establishConnection()) {
            String query = "INSERT INTO OrderInfo (OrderID, CustomerID, PlacedOrders) VALUES (?,?,?)";
            System.out.println("Query statement: " + query);

            try (PreparedStatement pStatement = connection.prepareStatement(query)) {
                pStatement.setInt(1, order.getOrderID());
                pStatement.setInt(2, order.getCustomerID());
                pStatement.setInt(3, order.getPlacedOrders());
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
    public List<OrderInfo> getAllOrders() {
        List<OrderInfo> orders = new ArrayList<>();
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("SELECT * FROM OrderInfo");
                ResultSet result = pStatement.getResultSet();
                while (result.next()) {
                    int OrderID = result.getInt("OrderID");
                    int CustomerID = result.getInt("CustomerID");
                    int PlacedOrders = result.getInt("PlacedOrders");
                    orders.add(new OrderInfo(OrderID, CustomerID, PlacedOrders));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return orders;
    }

    @Override
    public boolean updateOrderInfo(OrderInfo order) {
        try (Connection connection = establishConnection()) {
            try (PreparedStatement pStatement = connection.prepareStatement("UPDATE OrderInfo SET CustomerID=?, PlacedOrders=? WHERE OrderID=?")) {
                pStatement.setInt(1, order.getCustomerID());
                pStatement.setInt(2, order.getPlacedOrders());
                pStatement.setInt(3, order.getOrderID());
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
    public boolean deleteOrderInfo(int OrderID) {
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("DELETE FROM OrderInfo WHERE OrderID = " + OrderID);
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
    public OrderInfo getOrderById(int ID) {
        OrderInfo order = null;
        try (Connection connection = establishConnection()) {
            try (Statement pStatement = connection.createStatement()) {
                pStatement.execute("SELECT * FROM OrderInfo WHERE OrderID = " + ID);
                ResultSet result = pStatement.getResultSet();
                while (result.next()) {
                    int OrderID = result.getInt("OrderID");
                    int CustomerID = result.getInt("CustomerID");
                    int PlacedOrders = result.getInt("PlacedOrders");
                    order = new OrderInfo(OrderID, CustomerID, PlacedOrders);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return order;
    }
}
