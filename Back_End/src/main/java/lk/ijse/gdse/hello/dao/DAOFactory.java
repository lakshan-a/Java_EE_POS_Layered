package lk.ijse.gdse.hello.dao;


import lk.ijse.gdse66.hello.dao.custom.impl.CustomerDaoImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER

    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
//            case ITEM:
//                return (T) new ItemDAOImpl();
//            case ORDER:
//                return (T) new OrderDAOImpl();
//            case ORDER_DETAILS:
//                return (T) new OrderDetailsDAOImpl();
//            case QUERY_DAO:
//                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }


}
