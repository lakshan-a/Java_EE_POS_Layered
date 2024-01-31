package lk.ijse.gdse.hello.api;

import jakarta.servlet.http.HttpServlet;
import lk.ijse.gdse.hello.bo.BoFactory;
import lk.ijse.gdse.hello.bo.custom.ItemBo;

public class ItemServlet extends HttpServlet {

    ItemBo itemBO = (ItemBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ITEM);

}
