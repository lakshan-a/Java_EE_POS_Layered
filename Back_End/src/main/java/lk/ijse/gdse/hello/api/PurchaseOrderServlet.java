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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Content-Type", "application/json");

        JsonReader reader = Json.createReader (req.getReader ());
        JsonObject jsonObject = reader.readObject ();

        String oID = jsonObject.getString ("oID");
        String oDate = jsonObject.getString ("oDate");
        String oCusID = jsonObject.getString ("oCusID");

        System.out.println ("Customer"+oID+""+oDate+""+oCusID);

        JsonArray oCartItems = jsonObject.getJsonArray ("oCartItems");

        try {
            Class.forName ("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/thogakade", "root", "12345");
            connection.setAutoCommit (false);

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

        } catch (ClassNotFoundException | SQLException e) {
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
