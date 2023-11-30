package AccessObjects;
import java.util.List;
import Entity.BookOrderInfo;

public interface AccessBookOrderInfo {
    boolean createBookOrderInfo(BookOrderInfo bookOrder);
    List<BookOrderInfo> getAllBookOrders();
    boolean updateBookOrderInfo(BookOrderInfo bookOrder);
    boolean deleteBookOrderInfo(int OrderID, int BookID);
    BookOrderInfo getBookOrderByOrderID(int OrderID);
}
