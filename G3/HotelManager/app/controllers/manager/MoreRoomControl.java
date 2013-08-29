package controllers.manager;

import models.RoomManagerDaytime;
import models.RoomTypeDaytime;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-26
 * Time: 上午8:37
 * To change this template use File | Settings | File Templates.
 */
public class MoreRoomControl extends Controller {
//    public static String savedtf(String roomtype,String starttime,String endtime,String roomnumber,String roomprice){
//        RoomManagerDaytimeF roommanagerdtf = new RoomManagerDaytimeF();
//
//        RoomTypeDaytime roomtypedt = RoomTypeDaytime.find("byHotel_idAndRoomtype",session.get("hotelid"),roomtype).first();
//        RoomManagerDaytimeF oldroommanagerdtf = RoomManagerDaytimeF.find("byRoomtypedaytime_id",roomtypedt.id).first();
//
//        if(oldroommanagerdtf == null){
//            roommanagerdtf.roomtype = roomtype;
//            roommanagerdtf.starttime = starttime;
//            roommanagerdtf.endtime = endtime;
//            roommanagerdtf.roomnumber = roomnumber;
//            roommanagerdtf.roomprice = roomprice;
//            roommanagerdtf.roomtypedaytime = roomtypedt;
//            roommanagerdtf.create();
//        }else{
//            oldroommanagerdtf.roomtype = roomtype;
//            oldroommanagerdtf.starttime = starttime;
//            oldroommanagerdtf.endtime = endtime;
//            oldroommanagerdtf.roomnumber = roomnumber;
//            oldroommanagerdtf.roomprice = roomprice;
//            oldroommanagerdtf.save();
//        }
//        String msg = roomtypedt.id;
//        return msg;
//    }
//
//    public static String savedtt(String roomtype,String starttime,String endtime,String roomnumber,String roomprice){
//        RoomManagerDaytimeT roommanagerdtt = new RoomManagerDaytimeT();
//
//        RoomTypeDaytime roomtypedt = RoomTypeDaytime.find("byHotel_idAndRoomtype",session.get("hotelid"),roomtype).first();
//        RoomManagerDaytimeT oldroommanagerdtt = RoomManagerDaytimeT.find("byRoomtypedaytime_id",roomtypedt.id).first();
//
//        if(oldroommanagerdtt == null){
//            roommanagerdtt.roomtype = roomtype;
//            roommanagerdtt.starttime = starttime;
//            roommanagerdtt.endtime = endtime;
//            roommanagerdtt.roomnumber = roomnumber;
//            roommanagerdtt.roomprice = roomprice;
//            roommanagerdtt.roomtypedaytime = roomtypedt;
//            roommanagerdtt.create();
//        }else{
//            oldroommanagerdtt.roomtype = roomtype;
//            oldroommanagerdtt.starttime = starttime;
//            oldroommanagerdtt.endtime = endtime;
//            oldroommanagerdtt.roomnumber = roomnumber;
//            oldroommanagerdtt.roomprice = roomprice;
//            oldroommanagerdtt.save();
//        }
//        String msg = roomtypedt.id;
//        return msg;
//    }
//
    public static String selectdtf(){
        String id = session.get("hotelid");
        //找到当前酒店的所有日间房型
        List<RoomTypeDaytime> roomtypedaytimes = RoomTypeDaytime.find("byHotel_id",id).fetch();
        List<RoomManagerDaytime> roommanagerdaytimes = new ArrayList<RoomManagerDaytime>();
        //遍历所有日间房型
        for (Iterator i = roomtypedaytimes.iterator(); i.hasNext();){
            //获取日间房型对象
            RoomTypeDaytime roomtypedaytime = (RoomTypeDaytime) i.next();
            //根据日间房型对应的ID号，并依据房型的ID号找到对应的余房对象
            List<RoomManagerDaytime> roommanagerdaytime = RoomManagerDaytime.find("byRoomtypedaytime_id",roomtypedaytime.id).fetch();
            for(int j=0;j<roommanagerdaytime.size();j++){
                //将所找到的余房对象加入到日间房余房的对象列表中
                roommanagerdaytimes.add(roommanagerdaytime.get(j));
            }
        }
        String date = RoomManagerDaytime.tohtml(roommanagerdaytimes);
        //返回Html
        return date;
    }
//
//    public static String selectdtt(){
//        String id = session.get("hotelid");
//        //找到当前酒店的所有日间房型
//        List<RoomTypeDaytime> roomtypedaytimes = RoomTypeDaytime.find("byHotel_id",id).fetch();
//        List<RoomManagerDaytime> roommanagerdaytimets = new ArrayList<RoomManagerDaytime>();
//        //遍历所有日间房型
//        for (Iterator i = roomtypedaytimes.iterator(); i.hasNext();){
//            //获取日间房型对象
//            RoomTypeDaytime roomtypedaytime = (RoomTypeDaytime) i.next();
//            //根据日间房型对应的ID号，并依据房型的ID号找到对应的余房对象
//            RoomManagerDaytime roommanagerdaytimet = RoomManagerDaytime.find("byRoomtypedaytime_id",roomtypedaytime.id).first();
//            //将所找到的余房对象加入到日间房余房的对象列表中
//            if(roommanagerdaytimet != null)
//                roommanagerdaytimets.add(roommanagerdaytimet);
//        }
//        String date = RoomManagerDaytime.tohtml(roommanagerdaytimets);
//        //返回Html
//        return date;
//    }
//
//    public static String deletedtf(String roomtype){
//        String id = session.get("hotelid");
//        RoomTypeDaytime room = RoomTypeDaytime.find("byRoomtypeAndHotel_id",roomtype,id).first();
//        RoomManagerDaytimeF roommanagerdaytimeF = RoomManagerDaytimeF.find("byRoomtypedaytime_id",room.id).first();
//        roommanagerdaytimeF.delete();
//        //返回Html
//        return "delete success!";
//    }
//
//    public static String deletedtt(String roomtype){
//        String id = session.get("hotelid");
//        RoomTypeDaytime room = RoomTypeDaytime.find("byRoomtypeAndHotel_id",roomtype,id).first();
//        RoomManagerDaytimeT roommanagerdaytimet = RoomManagerDaytimeT.find("byRoomtypedaytime_id",room.id).first();
//        roommanagerdaytimet.delete();
//        //返回Html
//        return "delete success!";
//    }
}
