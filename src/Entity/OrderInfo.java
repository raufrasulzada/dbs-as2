package Entity;

public class OrderInfo extends CustomerInfo{
    private int OrderID;
    private int CustomerID;
    private int PlacedOrders;
    private double TotalPrice;

    public OrderInfo() {
      
    }

    public OrderInfo(int OrderID, int CustomerID, int PlacedOrders, double TotalPrice) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.PlacedOrders = PlacedOrders;
        this.TotalPrice = TotalPrice;
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

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String toString() {
        return "OrderInfo{" +
                "OrderID=" + OrderID +
                ", CustomerID=" + CustomerID +
                ", placedOrders=" + PlacedOrders +
                ", totalPrice=" + TotalPrice +
                '}';
    }
}
