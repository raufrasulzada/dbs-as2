package Entity;

public class OrderInfo {
    private int OrderID;
    private int CustomerID;
    private int PlacedOrders;

    public OrderInfo() {
      
    }

    public OrderInfo(int OrderID, int CustomerID, int PlacedOrders) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.PlacedOrders = PlacedOrders;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomer(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getPlacedOrders() {
        return PlacedOrders;
    }

    public void setPlacedOrders(int PlacedOrders) {
        this.PlacedOrders = PlacedOrders;
    }

    public String toString() {
        return "OrderInfo{" +
                "OrderID=" + OrderID +
                ", CustomerID=" + CustomerID +
                ", placedOrders=" + PlacedOrders +
                '}';
    }
}
