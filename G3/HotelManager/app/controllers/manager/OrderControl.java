package controllers.manager;

import models.OrderDaytime;
import models.OrderNighttime;
import play.mvc.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-24
 * Time: 下午10:57
 * To change this template use File | Settings | File Templates.
 */
public class OrderControl extends Controller {
    public static String select(){
        String id = session.get("hotelid");
        List<OrderDaytime> orderdt = OrderDaytime.find("byHotel_id",id).fetch();
        String date = OrderDaytime.tohtml(orderdt);
        //返回Html
        return date;
    }

    public static String selectnight(){
        String id = session.get("hotelid");
        List<OrderNighttime> orderdt = OrderNighttime.find("byHotel_id",id).fetch();
        String date = OrderNighttime.tohtml(orderdt);
        //返回Html
        return date;
    }
}
