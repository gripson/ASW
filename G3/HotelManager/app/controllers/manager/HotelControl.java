package controllers.manager;

import com.google.gson.Gson;
import models.Admin;
import models.Hotel;
import play.mvc.Controller;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-17
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class HotelControl extends Controller {

    public static void save(String hotelname,String details,String phone,String fax,String hoteladdress,double lowest_price){
         Hotel hotel = new Hotel();

         String hotelid = session.get("hotelid");

         Hotel oldhotel = Hotel.find("byId", hotelid).first();

         if(oldhotel == null){
            hotel.hotelName = hotelname;
            hotel.hotelAddress = hoteladdress;
            hotel.hotelPrice = lowest_price;
            hotel.details = details;
            hotel.fax = fax;
            hotel.telephone = phone;
            hotel.create();

            String username = session.get("admin");
            Admin admin = Admin.find("byUsername", username).first();
            admin.hotel = hotel;
            admin.save();

            session.put("hotelid", hotel.id);
         }else{
            oldhotel.hotelName = hotelname;
            oldhotel.hotelAddress = hoteladdress;
            oldhotel.hotelPrice = lowest_price;
            oldhotel.details = details;
            oldhotel.fax = fax;
            oldhotel.telephone = phone;
            oldhotel.save();
         }

         Application.hotel();
        // String msg="修改成功！";
    }

    public static void select(){
        String username = session.get("admin");
        Admin admin = Admin.find("byUsername", username).first();
        Hotel hotel = admin.hotel;

        renderJSON(new Gson().toJson(hotel).toString());
    }
}
