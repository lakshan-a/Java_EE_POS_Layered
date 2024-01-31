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
import lk.ijse.gdse.hello.bo.custom.PurchaseOrderBo;
import lk.ijse.gdse.hello.dto.OrderDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/placeOrder")
public class PurchaseOrderServlet extends HttpServlet {

    PurchaseOrderBo purchaseOrderBO= (PurchaseOrderBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PURCHASE_ORDER);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
