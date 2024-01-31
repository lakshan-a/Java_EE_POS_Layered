package lk.ijse.gdse.hello.api;

import jakarta.json.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.hello.bo.BoFactory;
import lk.ijse.gdse.hello.bo.custom.PurchaseOrderBo;
import lk.ijse.gdse.hello.dto.OrderDTO;
import lk.ijse.gdse.hello.dto.OrderDetailDTO;
import lk.ijse.gdse.hello.util.ResponseUtil;
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

//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
//        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

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

//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
//        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
//        JsonArray jsonArray=reader.readArray();
        String oID = jsonObject.getString("oID");
        String oDate = jsonObject.getString("oDate");
        String oCusID = jsonObject.getString("oCusID");
        String oItemID = jsonObject.getString("oItemID");
        String oItemName = jsonObject.getString("oItemName");
        String oUnitPrice = jsonObject.getString("oUnitPrice");
        String oQty = jsonObject.getString("oQty");
        String oQtyOnHnd =jsonObject.getString("oQtyOnHnd");
        JsonArray oCartItems = jsonObject.getJsonArray("oCartItems");

        ServletContext servletContext =getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try (Connection connection= pool.getConnection()){



            ArrayList<OrderDetailDTO> orderDetailsDTOS = new ArrayList<>();

            for (int i = 0; i < oCartItems.size(); i++) {
                String iId= oCartItems.getJsonArray(i).getString(0);
                int iQty= Integer.parseInt(oCartItems.getJsonArray(i).getString(3));
                double iPrice= Double.parseDouble(oCartItems.getJsonArray(i).getString(2));

                System.out.println(iId);
                System.out.println(iQty);
                System.out.println(iPrice);
                orderDetailsDTOS.add(new OrderDetailDTO(iId,oID,iQty,iPrice));
            }

            OrderDTO orderDTO = new OrderDTO(oID,oDate,oCusID,orderDetailsDTOS);
            if (purchaseOrderBO.purchaseOrder(connection,orderDTO)){
                resp.getWriter().print(ResponseUtil.getJson("OK","Successfully Added !"));
            }


        } catch (ClassNotFoundException e) {
            resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));

        } catch (SQLException e) {
            resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));

        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
//        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
