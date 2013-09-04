package controllers.manager;

import models.Statistical;
import play.mvc.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-30
 * Time: 下午8:50
 * To change this template use File | Settings | File Templates.
 */
public class StatisticalControl extends Controller {

    public static String selectall(){
        String id = session.get("hotelid");
        List<Statistical> statisticals = Statistical.find("byHotel_id",id).fetch();
        String date = Statistical.tohtml(statisticals);
        //返回Html
        return date;
    }

}
