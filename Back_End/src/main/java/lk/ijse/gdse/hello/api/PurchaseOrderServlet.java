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
import java.sql.*;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/orders")
public class PurchaseOrderServlet extends HttpServlet {

//    PurchaseOrderBo purchaseOrderBO= (PurchaseOrderBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PURCHASE_ORDER);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



//            resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Content-Type","application/json");

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

            try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "12345");
            PreparedStatement pstm = connection.prepareStatement("select * from orders");
//            PreparedStatement pstm2 = connection.prepareStatement("select * from order_detail");
            ResultSet rst = pstm.executeQuery();
//            ResultSet rst2 = pstm2.executeQuery();
            PrintWriter writer = resp.getWriter();



            JsonArrayBuilder allCustomers = Json.createArrayBuilder();


            while (rst.next()) {
                String orderID = rst.getString(1);
                String orderCusID = rst.getString(2);
                String orderDate = rst.getString(3);
//                String orderTotal = String.valueOf(rst.getInt(4));

                JsonObjectBuilder customer = Json.createObjectBuilder();

                customer.add("orderID",orderID);
                customer.add("orderCusID",orderCusID);
                customer.add("orderDate",orderDate);
//                customer.add("contact",contact);

                allCustomers.add(customer.build());
            }


            writer.print(allCustomers.build());


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String oID = jsonObject.getString("orderId");
        String oDate = jsonObject.getString("date");
        String oCusID = jsonObject.getString("cusId");
        JsonArray oCartItems = jsonObject.getJsonArray("itemDet");

        System.out.println(oCartItems);

        for (JsonValue obj : oCartItems){
            System.out.println(obj);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root","12345");
                connection.setAutoCommit(false);
                PreparedStatement pstm = connection.prepareStatement("insert into orders values(?,?,?)");
                pstm.setObject(1,oID);
                pstm.setObject(2,oDate);
                pstm.setObject(3,oCartItems);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }



        //int newQty=Integer.parseInt (qtyOnHand)-Integer.parseInt (oQty);


      /*  try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "12345");
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement("insert into orders values(?,?,?)");
            pstm.setObject(1, oID);
            pstm.setObject(2, oDate);
            pstm.setObject(3, oCusID);
            resp.addHeader("Content-Type", "application/json");

            if (pstm.executeUpdate() > 0) {

                for (int i = 0; i < oCartItems.size(); i++) {
                    PreparedStatement pstm2 = connection.prepareStatement("insert into orderdetail values(?,?,?,?)");
                    pstm2.setObject(1, oCartItems.getJsonArray(i).getString(0));
                    pstm2.setObject(2, oID);
                    pstm2.setObject(3, oCartItems.getJsonArray(i).getString(3));
                    pstm2.setObject(4, oCartItems.getJsonArray(i).getString(2));

                    if (pstm2.executeUpdate()>0){
                        connection.commit();
                        resp.addHeader("Content-Type", "application/json");
                        JsonObjectBuilder response = Json.createObjectBuilder();
                        response.add("state", "Ok");
                        response.add("message", "Successfully Added.!");
                        response.add("data", "");
                        resp.getWriter().print(response.build());
                    }else {
                        connection.rollback();
                    }

                }
            }
            else {
                connection.rollback();
            }

        } catch (ClassNotFoundException e) {
            resp.addHeader("Content-Type", "application/json");
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
            resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("state", "Error");
            response.add("message", e.getMessage());
            response.add("data", "");
            resp.setStatus(400);
            resp.getWriter().print(response.build());

        } catch (SQLException e) {
            resp.addHeader("Content-Type", "application/json");
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
            resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("state", "Error");
            response.add("message", e.getMessage());
            response.add("data", "");
            resp.setStatus(400);
            resp.getWriter().print(response.build());

        }*/
    }


//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addHeader("Access-Control-Allow-Origin","*");
//        resp.addHeader("Content-Type", "application/json");
//
//        JsonReader reader = Json.createReader (req.getReader ());
//        JsonObject jsonObject = reader.readObject ();
//
//        String oID = jsonObject.getString ("oID");
//        String oDate = jsonObject.getString ("oDate");
//        String oCusID = jsonObject.getString ("oCusID");
//
//        System.out.println ("Customer"+oID+""+oDate+""+oCusID);
//
//        JsonArray oCartItems = jsonObject.getJsonArray ("oCartItems");
//
//        try {
//            Class.forName ("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/thogakade", "root", "12345");
//            connection.setAutoCommit (false);
//
//            PreparedStatement pstm = connection.prepareStatement ("insert into orders values(?,?,?)");
//            pstm.setObject(1, oID);
//            pstm.setObject(2, oDate);
//            pstm.setObject(3, oCusID);
//
//            if(!(pstm.executeUpdate ()>0)){
//                connection.rollback ();
//                connection.setAutoCommit (true);
//                throw new SQLException ("Order Not Added");
//            }
//
//            for (JsonValue oCartItem : oCartItems) {
//                JsonObject odObject = oCartItem.asJsonObject ();
//
//                String itemCode = odObject.getString ("itemCode");
//                String itemPrice = odObject.getString ("itemPrice");
//                String qty = odObject.getString ("qty");
//                String avQty=odObject.getString ("qtyOnHand");
//
//                System.out.println ("Item"+itemCode+""+itemPrice+""+qty+""+avQty);
//
//                PreparedStatement pstm2= connection.prepareStatement ("insert into orderdetail values(?,?,?,?)");
//                pstm2.setObject (1,itemCode);
//                pstm2.setObject (2,oID);
//                pstm2.setObject (3,qty);
//                pstm2.setObject (4,itemPrice);
//
//                if (!(pstm2.executeUpdate ()>0)){
//                    connection.rollback ();
//                    connection.setAutoCommit (true);
//                    throw new SQLException ("Order-Detail Not Added...");
//                }
//
//                PreparedStatement pstm3 = connection.prepareStatement("update item set qtyOnHand=? where code=?");
//                int newQty=Integer.parseInt (avQty)-Integer.parseInt (qty);
//                pstm3.setObject (1,newQty);
//                pstm3.setObject (2,itemCode);
//
//                if(!(pstm3.executeUpdate ()>0)){
//                    connection.rollback ();
//                    connection.setAutoCommit (true);
//                    throw new SQLException ("Item Qty Not Updated...");
//                }
//            }
//
//            connection.commit ();
//            connection.setAutoCommit (true);
//
//        } catch (ClassNotFoundException | SQLException e) {
//            JsonObjectBuilder response = Json.createObjectBuilder();
//            response.add("state", "Error");
//            response.add("message", e.getMessage());
//            response.add("data", "");
//            resp.setStatus(400);
//            resp.getWriter().print(response.build());
//        }
//
//    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
