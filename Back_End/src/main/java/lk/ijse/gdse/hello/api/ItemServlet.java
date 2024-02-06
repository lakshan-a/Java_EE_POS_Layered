package lk.ijse.gdse.hello.api;

import jakarta.json.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.hello.bo.BoFactory;
import lk.ijse.gdse.hello.bo.custom.ItemBo;
import lk.ijse.gdse.hello.dto.CustomerDTO;
import lk.ijse.gdse.hello.dto.ItemDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/items")
public class ItemServlet extends HttpServlet {

    ItemBo itemBO = (ItemBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
//        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");


        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");


        try (Connection connection = pool.getConnection()){
            PrintWriter writer = resp.getWriter();

            JsonArrayBuilder allItems = Json.createArrayBuilder();

            ArrayList<ItemDTO> all = itemBO.getAllItems(connection);

            for (ItemDTO itemDTO:all){
                JsonObjectBuilder item = Json.createObjectBuilder();

                item.add("code",itemDTO.getItemCode());
                item.add("name",itemDTO.getItemName());
                item.add("qty",itemDTO.getItemQty());
                item.add("price",itemDTO.getItemPrice());

                allItems.add(item.build());
            }

            writer.print(allItems.build());


        } catch (ClassNotFoundException e) {
            resp.getWriter().println(e.getMessage());
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin","*");


        ServletContext servletContext = getServletContext();
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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String code = jsonObject.getString("code");
        String name = jsonObject.getString("name");
        Double price = Double.valueOf(jsonObject.getString("price"));
        int qty = Integer.parseInt(jsonObject.getString("qty"));


        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");


        System.out.printf("code=%s ,name=%s ,price=%s ,qty=%s\n" , code,name,price,qty);

        try(Connection connection = pool.getConnection()) {
            PreparedStatement stn = connection.prepareStatement("UPDATE item SET description=?,unitPrice=?,qtyOnHand=? WHERE code=?");


            stn.setString(1,name);
            stn.setDouble(2,price);
            stn.setInt(3,qty);
            stn.setString(4,code);

            stn.executeUpdate();
            resp.getWriter().write("print!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");


        String code = req.getParameter("code");

        try(Connection connection = pool.getConnection()) {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM item WHERE code=?");

            stm.setString(1,code);

            if(stm.executeUpdate() != 0){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else{
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete the customer!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
