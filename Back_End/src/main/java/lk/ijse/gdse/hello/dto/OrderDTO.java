package lk.ijse.gdse.hello.dto;

public class OrderDTO {

    private String orderID;
    private String date;
    private String customerID;

    public OrderDTO(String orderID, String date, String customerID) {
        this.orderID = orderID;
        this.date = date;
        this.customerID = customerID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", customerID='" + customerID + '\'' +
                '}';
    }
}
