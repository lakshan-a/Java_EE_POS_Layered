package lk.ijse.gdse.hello.bo;

import lk.ijse.gdse.hello.bo.custom.impl.CustomerBoImpl;
import lk.ijse.gdse.hello.bo.custom.impl.ItemBoImpl;
import lk.ijse.gdse.hello.bo.custom.impl.PurchaseOrderBoImpl;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){
    }
    public static BoFactory getBoFactory(){

        return (boFactory==null)? boFactory=new BoFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,PURCHASE_ORDER
    }

    //Object creation logic for BO objects
    public SuperBo getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBoImpl();
            case ITEM:
                return new ItemBoImpl();
            case PURCHASE_ORDER:
                return new PurchaseOrderBoImpl();
            default:
                return null;
        }
    }

}
