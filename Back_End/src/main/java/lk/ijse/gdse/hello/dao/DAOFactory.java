package lk.ijse.gdse.hello.dao;

import lk.ijse.gdse.hello.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.gdse.hello.dao.custom.impl.ItemDaoImpl;
import lk.ijse.gdse.hello.dao.custom.impl.OrderDaoImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,ITEM,ORDER

    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case ITEM:
                return (T) new ItemDaoImpl();
            case ORDER:
                return (T) new OrderDaoImpl();
//            case ORDER_DETAILS:
//                return (T) new OrderDetailsDAOImpl();
//            case QUERY_DAO:
//                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }


}
