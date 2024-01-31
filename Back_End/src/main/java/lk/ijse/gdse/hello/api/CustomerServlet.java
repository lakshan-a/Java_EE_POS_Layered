package lk.ijse.gdse.hello.api;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.hello.bo.BoFactory;
import lk.ijse.gdse.hello.bo.custom.CustomerBo;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {

    CustomerBo customerBO = (CustomerBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.CUSTOMER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = (ServletContext) getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");
        try (Connection connection = pool.getConnection()){

            PrintWriter writer = resp.getWriter();

            JsonArrayBuilder allCustomers = Json.createArrayBuilder();

            ArrayList<CustomerDTO> all = customerBO.getAllCustomers(connection);

            for (CustomerDTO customerDTO:all){
                JsonObjectBuilder customer = Json.createObjectBuilder();

                customer.add("id",customerDTO.getCusId());
                customer.add("name",customerDTO.getCusName());
                customer.add("address",customerDTO.getCusAddress());
                customer.add("salary",customerDTO.getCusSalary());

                allCustomers.add(customer.build());
            }
            writer.print(allCustomers.build());


        } catch (ClassNotFoundException e) {
            resp.getWriter().println(e.getMessage());
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.addHeader("Access-Control-Allow-Origin","*");


        jakarta.servlet.ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");


        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String salary = req.getParameter("salary");

        System.out.printf("id=%s ,name=%s ,address=%s\n" , id,name,address);


        try (Connection connection = pool.getConnection()){
            PreparedStatement stn = connection.prepareStatement("INSERT INTO customer(id,name,address,salary) VALUES(?,?,?,?)");

            stn.setString(1,id);
            stn.setString(2,name);
            stn.setString(3,address);
            stn.setString(4,salary);

            stn.executeUpdate();
            resp.getWriter().write("print!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
