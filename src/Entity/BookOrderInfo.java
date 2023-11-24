package Entity;

public class BookOrderInfo extends Book{
    private int OrderID;
    private int BookID;

    public BookOrderInfo(int OrderID, int BookID) {
        this.OrderID = OrderID;
        this.BookID = BookID;
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

    @Override
    public String toString() {
        return "BookOrderInfo{" +
                "OrderID=" + OrderID +
                ", BookID='" + BookID + '\'' +
                '}';
    }
}
