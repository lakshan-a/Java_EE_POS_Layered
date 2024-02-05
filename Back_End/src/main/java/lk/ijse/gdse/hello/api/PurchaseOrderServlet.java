package lk.ijse.gdse.hello.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.hello.dao.SQLUtil;
import lk.ijse.gdse.hello.db.DBConnection;
import lk.ijse.gdse.hello.dto.OrderDTO;
import lk.ijse.gdse.hello.dto.OrderDetailDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/orders")
public class PurchaseOrderServlet extends HttpServlet {


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
