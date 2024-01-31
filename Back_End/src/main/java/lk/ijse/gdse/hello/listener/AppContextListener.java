package lk.ijse.gdse.hello.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;


@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext is initialized");

        BasicDataSource dbcp = new BasicDataSource(); //create a connection pool
        dbcp.setUsername("root");
        dbcp.setPassword("12345");
        dbcp.setUrl("jdbc:mysql://localhost:3306/thogakade");
        dbcp.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbcp.setInitialSize(2);
        dbcp.setMaxTotal(5);

        ServletContext sc = sce.getServletContext();
        sc.setAttribute("dbcp",dbcp);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext is destroyed");
    }
}
