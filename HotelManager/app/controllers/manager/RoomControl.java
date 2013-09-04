package controllers.manager;

import com.google.gson.Gson;
import models.*;
import play.mvc.Controller;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-24
 * Time: 下午12:11
 * To change this template use File | Settings | File Templates.
 */
public class RoomControl extends Controller {
    public static String fixtypesave(int addpreroom,String addroomtype,String addbedtype,String addbreakfast,String addnetwork,double addloveprice,String addarea,int addfloot,int addbednumber,double addprice,double fixedcommission){
        RoomTypeDaytime roomtyped = new RoomTypeDaytime();
        Hotel hotel = Hotel.find("byId",session.get("hotelid")).first();
        RoomTypeDaytime oldroomtyped = RoomTypeDaytime.find("byRoomtypeAndHotel_id", addroomtype,hotel.id).first();


        if(oldroomtyped == null){
            roomtyped.hotel = hotel;
            roomtyped.roomtype = addroomtype;
            roomtyped.bed = addbedtype;
            roomtyped.breakfast = addbreakfast;
            roomtyped.network = addnetwork;
            roomtyped.loveprice = addloveprice;
            roomtyped.area = addarea;
            roomtyped.floor =addfloot;
            roomtyped.bednumber = addbednumber;
            roomtyped.publicprice = addprice;
            roomtyped.fixedcommission = fixedcommission;
            roomtyped.commissionrate = 0;
            roomtyped.preroom = addpreroom;
            roomtyped.state = "正在审核";
            roomtyped.create();
        }else{
            oldroomtyped.roomtype = addroomtype;
            oldroomtyped.bed = addbedtype;
            oldroomtyped.breakfast = addbreakfast;
            oldroomtyped.network = addnetwork;
            oldroomtyped.loveprice = addloveprice;
            oldroomtyped.area = addarea;
            oldroomtyped.floor =addfloot;
            oldroomtyped.bednumber = addbednumber;
            oldroomtyped.publicprice = addprice;
            oldroomtyped.fixedcommission = fixedcommission;
            oldroomtyped.commissionrate = 0;
            oldroomtyped.preroom = addpreroom;
            oldroomtyped.state = "正在审核";
            oldroomtyped.save();
        }
        String msg = session.get("hotelid");
        return msg;
    }

    public static String ratetypesave(int addpreroom2,String addroomtype2,String addbedtype2,String addbreakfast2,String addnetwork2,double addloveprice2,String addarea2,int addfloot2,int addbednumber2,double addprice2,double commissionrate){
        RoomTypeDaytime roomtypen = new RoomTypeDaytime();

        RoomTypeDaytime oldroomtypen = RoomTypeDaytime.find("byRoomtype", addroomtype2).first();
        Hotel hotel = Hotel.find("byId",session.get("hotelid")).first();
        if(oldroomtypen == null){
            roomtypen.hotel = hotel;
            roomtypen.roomtype = addroomtype2;
            roomtypen.bed = addbedtype2;
            roomtypen.breakfast = addbreakfast2;
            roomtypen.network = addnetwork2;
            roomtypen.loveprice = addloveprice2;
            roomtypen.area = addarea2;
            roomtypen.floor =addfloot2;
            roomtypen.bednumber = addbednumber2;
            roomtypen.publicprice = addprice2;
            roomtypen.commissionrate = commissionrate;
            roomtypen.fixedcommission = 0;
            roomtypen.preroom = addpreroom2;
            roomtypen.state = "正在审核";
            roomtypen.create();
        }else{
            oldroomtypen.roomtype = addroomtype2;
            oldroomtypen.bed = addbedtype2;
            oldroomtypen.breakfast = addbreakfast2;
            oldroomtypen.network = addnetwork2;
            oldroomtypen.loveprice = addloveprice2;
            oldroomtypen.area = addarea2;
            oldroomtypen.floor =addfloot2;
            oldroomtypen.bednumber = addbednumber2;
            oldroomtypen.publicprice = addprice2;
            oldroomtypen.commissionrate = commissionrate;
            oldroomtypen.fixedcommission = 0;
            oldroomtypen.preroom = addpreroom2;
            oldroomtypen.state = "正在审核";
            oldroomtypen.save();
        }
        String msg ="Success!";
        return msg;
    }
    //夜间房
    public static String nightfixtypesave(String addroomtype,String addbedtype,String addbreakfast,String addnetwork,double addloveprice,String addarea,int addfloot,int addbednumber,double addprice,double fixedcommission){
        RoomTypeNighttime roomtyped = new RoomTypeNighttime();

        RoomTypeNighttime oldroomtyped = RoomTypeNighttime.find("byRoomtype", addroomtype).first();
        Hotel hotel = Hotel.find("byId",session.get("hotelid")).first();
        if(oldroomtyped == null){
            roomtyped.hotel = hotel;
            roomtyped.roomtype = addroomtype;
            roomtyped.bed = addbedtype;
            roomtyped.breakfast = addbreakfast;
            roomtyped.network = addnetwork;
            roomtyped.loveprice = addloveprice;
            roomtyped.area = addarea;
            roomtyped.floor =addfloot;
            roomtyped.bednumber = addbednumber;
            roomtyped.publicprice = addprice;
            roomtyped.fixedcommission = fixedcommission;
            roomtyped.commissionrate = 0;
            roomtyped.state = "正在审核";
            roomtyped.create();
        }else{
            oldroomtyped.roomtype = addroomtype;
            oldroomtyped.bed = addbedtype;
            oldroomtyped.breakfast = addbreakfast;
            oldroomtyped.network = addnetwork;
            oldroomtyped.loveprice = addloveprice;
            oldroomtyped.area = addarea;
            oldroomtyped.floor =addfloot;
            oldroomtyped.bednumber = addbednumber;
            oldroomtyped.publicprice = addprice;
            oldroomtyped.fixedcommission = fixedcommission;
            oldroomtyped.commissionrate = 0;
            oldroomtyped.state = "正在审核";
            oldroomtyped.save();
        }
        String msg ="Success!";
        return msg;
    }

    public static String nightratetypesave(String addroomtype2,String addbedtype2,String addbreakfast2,String addnetwork2,double addloveprice2,String addarea2,int addfloot2,int addbednumber2,double addprice2,double commissionrate){
        RoomTypeNighttime roomtypen = new RoomTypeNighttime();

        RoomTypeNighttime oldroomtypen = RoomTypeNighttime.find("byRoomtype", addroomtype2).first();
        Hotel hotel = Hotel.find("byId",session.get("hotelid")).first();
        if(oldroomtypen == null){
            roomtypen.hotel = hotel;
            roomtypen.roomtype = addroomtype2;
            roomtypen.bed = addbedtype2;
            roomtypen.breakfast = addbreakfast2;
            roomtypen.network = addnetwork2;
            roomtypen.loveprice = addloveprice2;
            roomtypen.area = addarea2;
            roomtypen.floor =addfloot2;
            roomtypen.bednumber = addbednumber2;
            roomtypen.publicprice = addprice2;
            roomtypen.commissionrate = commissionrate;
            roomtypen.fixedcommission = 0;
            roomtypen.state = "正在审核";
            roomtypen.create();
        }else{
            oldroomtypen.roomtype = addroomtype2;
            oldroomtypen.bed = addbedtype2;
            oldroomtypen.breakfast = addbreakfast2;
            oldroomtypen.network = addnetwork2;
            oldroomtypen.loveprice = addloveprice2;
            oldroomtypen.area = addarea2;
            oldroomtypen.floor =addfloot2;
            oldroomtypen.bednumber = addbednumber2;
            oldroomtypen.publicprice = addprice2;
            oldroomtypen.commissionrate = commissionrate;
            oldroomtypen.fixedcommission = 0;
            oldroomtypen.state = "正在审核";
            oldroomtypen.save();
        }
        String msg ="Success!";
        return msg;
    }
//日间房
    public static String select(){
        String id = session.get("hotelid");
        List<RoomTypeDaytime> roomtdtime = RoomTypeDaytime.find("byHotel_id",id).fetch();
        String date = RoomTypeDaytime.tohtml(roomtdtime);
        //返回Html
        return date;
    }


//夜间房
    public static String selectnight(){
        String id = session.get("hotelid");
        List<RoomTypeNighttime> orderdt = RoomTypeNighttime.find("byHotel_id",id).fetch();
        String date = RoomTypeNighttime.tohtml(orderdt);
        //返回Html
        return date;
    }
//日间房
    public static void selectupdateinf(String roomtype){
        String id = session.get("hotelid");
        RoomTypeDaytime room = RoomTypeDaytime.find("byRoomtypeAndHotel_id",roomtype,id).first();
        //返回json
        renderJSON (new Gson().toJson(room).toString());
    }

    public static String deletedaytime(String roomtype){
        String id = session.get("hotelid");
        RoomTypeDaytime room = RoomTypeDaytime.find("byRoomtypeAndHotel_id",roomtype,id).first();
        room.state = "已删除";
        room.save();
        //返回Html
        return "delete success!";
    }

//夜间房
    public static void nightselectupdateinf(String roomtype){
        String id = session.get("hotelid");
        RoomTypeNighttime room = RoomTypeNighttime.find("byRoomtypeAndHotel_id",roomtype,id).first();
        //返回json
        renderJSON (new Gson().toJson(room).toString());
    }

    public static String deletenighttime(String roomtype){
        String id = session.get("hotelid");
        RoomTypeNighttime room = RoomTypeNighttime.find("byRoomtypeAndHotel_id",roomtype,id).first();
        room.state = "已删除";
        room.save();
        //返回Html
        return "delete success!";
    }
}
