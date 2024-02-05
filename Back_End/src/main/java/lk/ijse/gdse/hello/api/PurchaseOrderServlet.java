package lk.ijse.gdse.hello.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.hello.bo.BoFactory;
import lk.ijse.gdse.hello.bo.custom.PurchaseOrderBo;
import lk.ijse.gdse.hello.dao.SQLUtil;
import lk.ijse.gdse.hello.db.DBConnection;
import lk.ijse.gdse.hello.dto.OrderDTO;
import lk.ijse.gdse.hello.dto.OrderDetailDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/orders")
public class PurchaseOrderServlet extends HttpServlet {

    PurchaseOrderBo purchaseOrderBO= (PurchaseOrderBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PURCHASE_ORDER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Content-Type","application/json");

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        ServletContext servletContext =getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");
        PrintWriter writer = resp.getWriter();
        try (Connection connection=pool.getConnection()){

            JsonArrayBuilder allOrders = Json.createArrayBuilder();
            ArrayList<OrderDTO> all = purchaseOrderBO.getAllOrders(connection);

            for (OrderDTO orderDTO:all){
                JsonObjectBuilder order = Json.createObjectBuilder();
                order.add("oID",orderDTO.getOrderID());
                order.add("oDate", String.valueOf(orderDTO.getDate()));
                order.add("oCusID",orderDTO.getCustomerID());

                allOrders.add(order).build();


            }
            writer.println(allOrders.build());

        } catch (ClassNotFoundException e) {
            resp.getWriter().println(e.getMessage());
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");


        ArrayList<OrderDetailDTO> orderDetailsDTOS = new ArrayList<>();


        ServletContext servletContext =getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String oID = jsonObject.getString("orderId");
        String oDate = jsonObject.getString("date");
        String oCusID = jsonObject.getString("cusId");
        JsonArray oCartItems = jsonObject.getJsonArray("itemDet");


        System.out.println(oCusID);
        System.out.println(oDate);
        System.out.println(oID);

        try {
            Connection connection = pool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders VALUES (?,?,?)");

            preparedStatement.setString(1,oID);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(3,oCusID);

            int i = preparedStatement.executeUpdate();
            connection.commit();

            if (i>0){

                Connection connection1 = pool.getConnection();
                connection1.setAutoCommit(false);

                PreparedStatement preparedStatement1 = connection1.prepareStatement("INSERT INTO orderdetail VALUES (?,?,?,?)");

                for (JsonValue obj : oCartItems){

                    JsonObject jsonObject1 = obj.asJsonObject();
                    String code = jsonObject1.getString("code");
                    Integer avQty = Integer.valueOf(jsonObject1.getString("avQty"));
                    Double price = Double.valueOf(jsonObject1.getString("price"));

                    preparedStatement1.setString(1,oID);
                    preparedStatement1.setString(2,code );
                    preparedStatement1.setInt(3,avQty);
                    preparedStatement1.setDouble(4,price);

                    preparedStatement1.executeUpdate();


                }
                connection1.commit();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }

    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
