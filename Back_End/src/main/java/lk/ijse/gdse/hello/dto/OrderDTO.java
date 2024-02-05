package lk.ijse.gdse.hello.dto;

import java.util.List;

public class OrderDTO {
    private String orderID;

    private String date;


    private String customerID;

    public OrderDTO(String orderID, String date, String customerID) {
        this.orderID=orderID;
        this.date= String.valueOf(date);
        this.customerID=customerID;
    }

    public List<OrderDetailsDTO> getOrderDetailsDTOList() {
        return orderDetailsDTOList;
    }

    public void setOrderDetailsDTOList(List<OrderDetailsDTO> orderDetailsDTOList) {
        this.orderDetailsDTOList = orderDetailsDTOList;
    }

    List<OrderDetailsDTO> orderDetailsDTOList;

    public OrderDTO(String orderID, String date, String customerID , List orderDetailsDTOList) {
        this.orderID = orderID;
        this.date = date;
        this.customerID = customerID;
        this.orderDetailsDTOList=orderDetailsDTOList;
    }

    public OrderDTO() {
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
                ", date=" + date +
                ", customerID='" + customerID + '\'' +
                ", orderDetailsDTOList=" + orderDetailsDTOList +
                '}';
    }
}
