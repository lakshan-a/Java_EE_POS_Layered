package lk.ijse.gdse.hello.bo;

import lk.ijse.gdse.hello.bo.custom.impl.CustomerBoImpl;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){
    }

    public static BoFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BoFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER
    }

    public CustomerBoImpl getBO (BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBoImpl();
        }
        return null;
    }

}
