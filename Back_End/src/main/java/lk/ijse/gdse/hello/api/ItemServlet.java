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
                JsonObjectBuilder customer = Json.createObjectBuilder();

                customer.add("code",itemDTO.getItemCode());
                customer.add("name",itemDTO.getItemName());
                customer.add("qty",itemDTO.getItemQty());
                customer.add("price",itemDTO.getItemPrice());

                allItems.add(customer.build());
            }
//            Json json = JsonBuilder.create();
//            jsonb.toJson(customerList,resp.getWriter());

            writer.print(allItems.build());


        } catch (ClassNotFoundException e) {
            resp.getWriter().println(e.getMessage());
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
