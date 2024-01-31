package lk.ijse.gdse.hello.api;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.hello.bo.BoFactory;
import lk.ijse.gdse.hello.bo.custom.ItemBo;
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

        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try(Connection connection = pool.getConnection()) {

            PrintWriter writer = resp.getWriter();

            JsonArrayBuilder allItems = Json.createArrayBuilder();

            ArrayList<ItemDTO> all = itemBO.getAllItems(connection);

            for (ItemDTO itemDTO:all) {

                JsonObjectBuilder item = Json.createObjectBuilder();

                item.add("code",itemDTO.getItemCode());
                item.add("desc",itemDTO.getItemName());
                item.add("unitPrice",itemDTO.getItemPrice());
                item.add("qty",itemDTO.getItemQty());

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

        jakarta.servlet.ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");


        String code = req.getParameter("code");
        String description = req.getParameter("description");
        double unitPrice = Double.parseDouble(req.getParameter("unitPrice"));
        int qty = Integer.parseInt(req.getParameter("qtyOnHand"));

        System.out.printf("code=%s ,description=%s ,unitPrice=%s ,qtyOnHand=%s\n" , code,description,unitPrice,qty);


        try (Connection connection = pool.getConnection()){
            PreparedStatement stn = connection.prepareStatement("INSERT INTO item(code,description,unitPrice,qtyOnHand) VALUES(?,?,?,?)");

            stn.setString(1,code);
            stn.setString(2,description);
            stn.setDouble(3,unitPrice);
            stn.setInt(4,qty);

            stn.executeUpdate();
            resp.getWriter().write("print!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin","*");

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        String code = req.getParameter("code");
        String description = req.getParameter("description");
        double unitPrice = Double.parseDouble(req.getParameter("unitPrice"));
        int qty = Integer.parseInt(req.getParameter("qtyOnHand"));

        System.out.printf("code=%s ,description=%s ,unitPrice=%s ,qtyOnHand=%s\n" , code,description,unitPrice,qty);

        try (Connection connection = pool.getConnection()){
            PreparedStatement stn = connection.prepareStatement("UPDATE item SET description=?,unitPrice=?,qtyOnHand=? WHERE code=?");

            stn.setString(1,code);
            stn.setString(2,description);
            stn.setDouble(3,unitPrice);
            stn.setInt(4,qty);

            stn.executeUpdate();
            resp.getWriter().write("print!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.addHeader("Access-Control-Allow-Origin", "*");


        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        String code = req.getParameter("code");

        try (Connection connection =pool.getConnection()){
            PreparedStatement stm = connection.prepareStatement("DELETE FROM item WHERE code=?");
            stm.setString(1,code);

            if(stm.executeUpdate() != 0){
                resp.setStatus(javax.servlet.http.HttpServletResponse.SC_NO_CONTENT);
            }else{
                resp.sendError(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete the customer!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
