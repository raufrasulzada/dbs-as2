package Entity;

public class CustomerInfo {
  private int CustomerID;
  private String CustomerName;

  public CustomerInfo() {
    
  }

  public CustomerInfo(int CustomerID, String CustomerName){
    this.CustomerID = CustomerID;
    this.CustomerName = CustomerName;
  }

  public int getCustomerID () {
    return CustomerID;
  }
  public void setCustomerID(int CustomerID) {
    this.CustomerID = CustomerID;
  }

  public String getCustomerName () {
    return CustomerName;
  }
  public void setCustomerName(String CustomerName) {
    this.CustomerName = CustomerName;
  }

  public String toString() {
    return "CustomerInfo{" +
                "CustomerID=" + CustomerID +
                ", CustomerName='" + CustomerName + '\''+'}';
    }
  }