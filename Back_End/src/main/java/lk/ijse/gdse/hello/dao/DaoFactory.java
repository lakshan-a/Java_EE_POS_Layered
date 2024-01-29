package lk.ijse.gdse.hello.dao;

import lk.ijse.gdse.hello.dao.custom.impl.CustomerDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DaoFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, ITEM
    }

    public SuperDao getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDaoImpl();
//            case ITEM:
//                return new ItemDaoImpl();
            default:
                return null;
        }
    }
}
