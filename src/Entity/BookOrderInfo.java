package Entity;

public class BookOrderInfo {
    private int OrderID;
    private int BookID;
    private int PlacedOrders;

    public BookOrderInfo(int OrderID, int BookID, int PlacedOrders) {
        this.OrderID = OrderID;
        this.BookID = BookID;
        this.PlacedOrders = PlacedOrders;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public int getPlacedOrders() {
        return PlacedOrders;
    }

    public void setPlacedOrders(int PlacedOrders) {
        this.PlacedOrders = PlacedOrders;
    }

    @Override
    public String toString() {
        return "BookOrderInfo{" +
                "OrderID=" + OrderID +
                ", BookID='" + BookID  + "\'" +
                ", PlacedOrders=" + PlacedOrders +
                '}';
    }
}
