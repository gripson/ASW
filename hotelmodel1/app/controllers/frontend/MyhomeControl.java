package controllers.frontend;

import models.Favor;
import models.Hotel;
import models.User;
import play.mvc.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: Pinjie
 * Date: 13-8-15
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
public class MyhomeControl extends Controller {


    public static void saveMyinfo(User user){
        user.save();
        session.put("user",user.userName);
        Application.myHome(0);
    }

    public static  void deleteFavor(String hotelId){
        Favor favor=Favor.find("byHotelAndUser",Hotel.findById(hotelId),Application.connected()).first();
        favor.delete();
        Application.myHome(3);
    }

}
