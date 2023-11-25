package AccessObjects;
import java.util.List;
import Entity.CustomerInfo;

public interface AccessCustomerInfo {
    public boolean createCustomerInfo(CustomerInfo customer);
    public List<CustomerInfo> getAllCustomers();
    public boolean updateCustomerInfo(CustomerInfo customer);
    public boolean deleteCustomerInfo(int CustomerID);
    public CustomerInfo getCustomerById(int CustomerID);
}
