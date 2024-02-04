package lk.ijse.gdse.hello.entity;

import java.sql.Date;

public class Orders {

    private String orderID;
    private String date;
    private String customerID;

    public Orders(String orderID, String date, String customerID) {
        this.orderID = orderID;
        this.date = String.valueOf(date);
        this.customerID = customerID;
    }

    public Orders(String string, Date date, String rstString) {
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
}
