package Entity;

public class OrderInfo {
    private int OrderID;
    private int CustomerID;

    public OrderInfo() {
      
    }

    public OrderInfo(int OrderID, int CustomerID) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
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

    public String toString() {
        return "OrderInfo{" +
                "OrderID=" + OrderID +
                ", CustomerID=" + CustomerID +
                '}';
    }
}
