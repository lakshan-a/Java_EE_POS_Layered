package lk.ijse.gdse.hello.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustometDTO {

    private String cusId;
    private String cusName;
    private String cusAddress;
    private double cusSalary;

    public CustometDTO(String cusId, String cusName, String cusAddress, double cusSalary) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.cusSalary = cusSalary;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public double getCusSalary() {
        return cusSalary;
    }

    public void setCusSalary(double cusSalary) {
        this.cusSalary = cusSalary;
    }
}
