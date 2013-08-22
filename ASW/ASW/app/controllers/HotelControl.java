package controllers;

import models.Hotel;
import play.mvc.Controller;
/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-17
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class HotelControl extends Controller {

    public static void save(String hotelname,String phone,String activeStructure,String fax,String infraStructure,String address,double lowest_price,double cashback){
         Hotel hotel = new Hotel();

         Hotel oldhotel = Hotel.find("byHotelname", hotelname).first();

         if(oldhotel == null){
            hotel.hotelName = hotelname;
            hotel.hotelAddress = address;
            hotel.hotelPrice = lowest_price;
            hotel.hotelReturn = cashback;
            hotel.infraStructure = infraStructure;
            hotel.activeStructure = activeStructure;
            hotel.fax = fax;
            hotel.telephone = phone;
            hotel.create();
         }else{
            oldhotel.hotelName = hotelname;
            oldhotel.hotelAddress = address;
            oldhotel.hotelPrice = lowest_price;
            oldhotel.hotelReturn = cashback;
            oldhotel.infraStructure = infraStructure;
            oldhotel.activeStructure = activeStructure;
            oldhotel.fax = fax;
            oldhotel.telephone = phone;
            oldhotel.save();
         }


        // String msg="修改成功！";


    }
}
