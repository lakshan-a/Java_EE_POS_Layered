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
        CUSTOMER,ITEM,PO
    }

    //Object creation logic for BO objects
    public SuperBo getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBoImpl();
//            case ITEM:
//                return new ItemBOImpl();
//            case PO:
//                return new PurchaseOrderBOImpl();
            default:
                return null;
        }
    }

}
