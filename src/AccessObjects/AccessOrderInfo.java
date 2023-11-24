package AccessObjects;
import java.util.List;
import Entity.OrderInfo;

public interface AccessOrderInfo {
    boolean createOrderInfo(OrderInfo order);
    List<OrderInfo> getAllOrders();
    boolean updateOrderInfo(OrderInfo order);
    boolean deleteOrderInfo(int OrderID);
    OrderInfo getOrderById(int OrderID);
}