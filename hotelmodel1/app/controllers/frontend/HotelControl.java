package controllers.frontend;

import models.Hotel;
import play.mvc.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pinjie
 * Date: 13-7-16
 * Time: 上午11:04
 * To change this template use File | Settings | File Templates.
 */
public class HotelControl extends Controller {

    public static List<Hotel> loadHotel(int sort, int start, int num){
        if (sort == 1)
        {
            return sortOfDefault(start,num);
        }

        else if (sort == 2)
        {
            return sortOfLove(start,num);
        }

        else if (sort == 3)
        {
            return sortOfPriceAsc(start, num);
        }
        else if (sort == 4)
        {
            return sortOfPriceDesc(start,num);
        }

        else
        {
            return sortOfDefault(start, num);
        }
    }

    public static List<Hotel> sortOfDefault(int start, int num){
        List<Hotel> item = Hotel.all().from(start).fetch(num);
        return item;
    }


    public static List<Hotel> sortOfLove(int start, int num){
        List<Hotel> item = Hotel.all().from(start).fetch(num);
        return item;
    }

    public static List<Hotel> sortOfPriceAsc(int start, int num){
        List<Hotel> item =Hotel.find("order by hotelPrice asc").from(start).fetch(num);
        return item;
    }

    public static List<Hotel> sortOfPriceDesc(int start, int num){
        List<Hotel> item =Hotel.find("order by hotelPrice desc").from(start).fetch(num);
        return item;
    }

}
