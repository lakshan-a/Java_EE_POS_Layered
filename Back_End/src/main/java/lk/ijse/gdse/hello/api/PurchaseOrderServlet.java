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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/placeOrder")
public class PurchaseOrderServlet extends HttpServlet {

    PurchaseOrderBo purchaseOrderBO= (PurchaseOrderBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PURCHASE_ORDER);


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
//        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
//
//        ServletContext servletContext =getServletContext();
//        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");
//        PrintWriter writer = resp.getWriter();
//        try (Connection connection=pool.getConnection()){
//
//            JsonArrayBuilder allOrders = Json.createArrayBuilder();
//            ArrayList<OrderDTO> all = purchaseOrderBO.getAllOrders(connection);
//
//            for (OrderDTO orderDTO:all){
//                JsonObjectBuilder order = Json.createObjectBuilder();
//                order.add("oID",orderDTO.getOrderID());
//                order.add("oDate", String.valueOf(orderDTO.getDate()));
//                order.add("oCusID",orderDTO.getCustomerID());
//
//                allOrders.add(order).build();
//
//
//            }
//            writer.println(allOrders.build());
//
//        } catch (ClassNotFoundException e) {
//            resp.getWriter().println(e.getMessage());
//        } catch (SQLException e) {
//            resp.getWriter().println(e.getMessage());
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
//        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
//
//        JsonReader reader = Json.createReader(req.getReader());
//        JsonObject jsonObject = reader.readObject();
////        JsonArray jsonArray=reader.readArray();
//        String oID = jsonObject.getString("oID");
//        String oDate = jsonObject.getString("oDate");
//        String oCusID = jsonObject.getString("oCusID");
//        String oItemID = jsonObject.getString("oItemID");
//        String oItemName = jsonObject.getString("oItemName");
//        String oUnitPrice = jsonObject.getString("oUnitPrice");
//        String oQty = jsonObject.getString("oQty");
//        String oQtyOnHnd =jsonObject.getString("oQtyOnHnd");
//        JsonArray oCartItems = jsonObject.getJsonArray("oCartItems");
//
//        ServletContext servletContext =getServletContext();
//        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");
//
//        try (Connection connection= pool.getConnection()){
//
//
//
//            ArrayList<OrderDetailDTO> orderDetailsDTOS = new ArrayList<>();
//
//            for (int i = 0; i < oCartItems.size(); i++) {
//                String iId= oCartItems.getJsonArray(i).getString(0);
//                int iQty= Integer.parseInt(oCartItems.getJsonArray(i).getString(3));
//                double iPrice= Double.parseDouble(oCartItems.getJsonArray(i).getString(2));
//
//                System.out.println(iId);
//                System.out.println(iQty);
//                System.out.println(iPrice);
//                orderDetailsDTOS.add(new OrderDetailDTO(iId,oID,iQty,iPrice));
//            }
//
//            OrderDTO orderDTO = new OrderDTO(oID,oDate,oCusID,orderDetailsDTOS);
//            if (purchaseOrderBO.purchaseOrder(connection,orderDTO)){
//                resp.getWriter().print(ResponseUtil.getJson("OK","Successfully Added !"));
//            }
//
//
//        } catch (ClassNotFoundException e) {
//            resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));
//
//        } catch (SQLException e) {
//            resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));
//
//        }
//    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Content-Type", "application/json");

        JsonReader reader = Json.createReader (req.getReader ());
        JsonObject jsonObject = reader.readObject ();

        String oID = jsonObject.getString ("oID");
        String oDate = jsonObject.getString ("oDate");
        String oCusID = jsonObject.getString ("oCusID");

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        System.out.println ("Customer"+oID+""+oDate+""+oCusID);

        JsonArray oCartItems = jsonObject.getJsonArray ("oCartItems");

        try (Connection connection = pool.getConnection()){
//            Class.forName ("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/thogakade", "root", "12345");
//            connection.setAutoCommit (false);

            PreparedStatement pstm = connection.prepareStatement ("insert into orders values(?,?,?)");
            pstm.setObject(1, oID);
            pstm.setObject(2, oDate);
            pstm.setObject(3, oCusID);

            if(!(pstm.executeUpdate ()>0)){
                connection.rollback ();
                connection.setAutoCommit (true);
                throw new SQLException ("Order Not Added");
            }

            for (JsonValue oCartItem : oCartItems) {
                JsonObject odObject = oCartItem.asJsonObject ();

                String itemCode = odObject.getString ("itemCode");
                String itemPrice = odObject.getString ("itemPrice");
                String qty = odObject.getString ("qty");
                String avQty=odObject.getString ("qtyOnHand");

                System.out.println ("Item"+itemCode+""+itemPrice+""+qty+""+avQty);

                PreparedStatement pstm2= connection.prepareStatement ("insert into orderdetail values(?,?,?,?)");
                pstm2.setObject (1,itemCode);
                pstm2.setObject (2,oID);
                pstm2.setObject (3,qty);
                pstm2.setObject (4,itemPrice);

                if (!(pstm2.executeUpdate ()>0)){
                    connection.rollback ();
                    connection.setAutoCommit (true);
                    throw new SQLException ("Order-Detail Not Added...");
                }

                PreparedStatement pstm3 = connection.prepareStatement("update item set qtyOnHand=? where code=?");
                int newQty=Integer.parseInt (avQty)-Integer.parseInt (qty);
                pstm3.setObject (1,newQty);
                pstm3.setObject (2,itemCode);

                if(!(pstm3.executeUpdate ()>0)){
                    connection.rollback ();
                    connection.setAutoCommit (true);
                    throw new SQLException ("Item Qty Not Updated...");
                }
            }

            connection.commit ();
            connection.setAutoCommit (true);

        } catch (SQLException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("state", "Error");
            response.add("message", e.getMessage());
            response.add("data", "");
            resp.setStatus(400);
            resp.getWriter().print(response.build());
        }

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
