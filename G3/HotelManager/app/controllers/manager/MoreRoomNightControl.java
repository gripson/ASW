package controllers.manager;

import models.*;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-26
 * Time: 下午1:03
 * To change this template use File | Settings | File Templates.
 */
public class MoreRoomNightControl extends Controller {
//    public static String savedtf(String roomtype,String starttime,String endtime,String roomnumber,String roomprice){
//        RoomManagerNighttimeF roommanagerdtf = new RoomManagerNighttimeF();
//
//        RoomTypeNighttime roomtypedt = RoomTypeNighttime.find("byHotel_idAndRoomtype",session.get("hotelid"),roomtype).first();
//        RoomManagerNighttimeF oldroommanagerdtf = RoomManagerNighttimeF.find("byRoomtypenighttime_id",roomtypedt.id).first();
//
//        if(oldroommanagerdtf == null){
//            roommanagerdtf.roomtype = roomtype;
//            roommanagerdtf.starttime = starttime;
//            roommanagerdtf.endtime = endtime;
//            roommanagerdtf.roomnumber = roomnumber;
//            roommanagerdtf.roomprice = roomprice;
//            roommanagerdtf.roomtypenighttime = roomtypedt;
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
//        RoomManagerNighttimeT roommanagerdtt = new RoomManagerNighttimeT();
//
//        RoomTypeNighttime roomtypedt = RoomTypeNighttime.find("byHotel_idAndRoomtype",session.get("hotelid"),roomtype).first();
//        RoomManagerNighttimeT oldroommanagerdtt = RoomManagerNighttimeT.find("Roomtypenighttime_id",roomtypedt.id).first();
//
//        if(oldroommanagerdtt == null){
//            roommanagerdtt.roomtype = roomtype;
//            roommanagerdtt.starttime = starttime;
//            roommanagerdtt.endtime = endtime;
//            roommanagerdtt.roomnumber = roomnumber;
//            roommanagerdtt.roomprice = roomprice;
//            roommanagerdtt.roomtypenighttime = roomtypedt;
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
//    public static String selectdtf(){
//        String id = session.get("hotelid");
//        //找到当前酒店的所有日间房型
//        List<RoomTypeNighttime> roomtypedaytimes = RoomTypeNighttime.find("byHotel_id",id).fetch();
//        List<RoomManagerNighttimeF> roommanagerdaytimefs = new ArrayList<RoomManagerNighttimeF>();
//        //遍历所有日间房型
//        for (Iterator i = roomtypedaytimes.iterator(); i.hasNext();){
//            //获取日间房型对象
//            RoomTypeNighttime roomtypedaytime = (RoomTypeNighttime) i.next();
//            //根据日间房型对应的ID号，并依据房型的ID号找到对应的余房对象
//            RoomManagerNighttimeF roommanagerdaytimef = RoomManagerNighttimeF.find("byRoomtypenighttime_id",roomtypedaytime.id).first();
//            //将所找到的余房对象加入到日间房余房的对象列表中
//            if(roommanagerdaytimef != null)
//                roommanagerdaytimefs.add(roommanagerdaytimef);
//        }
//        String date = RoomManagerNighttimeF.tohtml(roommanagerdaytimefs);
//        //返回Html
//        return date;
//    }
//
//    public static String selectdtt(){
//        String id = session.get("hotelid");
//        //找到当前酒店的所有日间房型
//        List<RoomTypeNighttime> roomtypedaytimes = RoomTypeNighttime.find("byHotel_id",id).fetch();
//        List<RoomManagerNighttimeT> roommanagerdaytimets = new ArrayList<RoomManagerNighttimeT>();
//        //遍历所有日间房型
//        for (Iterator i = roomtypedaytimes.iterator(); i.hasNext();){
//            //获取日间房型对象
//            RoomTypeNighttime roomtypedaytime = (RoomTypeNighttime) i.next();
//            //根据日间房型对应的ID号，并依据房型的ID号找到对应的余房对象
//            RoomManagerNighttimeT roommanagerdaytimet = RoomManagerNighttimeT.find("byRoomtypenighttime_id",roomtypedaytime.id).first();
//            //将所找到的余房对象加入到日间房余房的对象列表中
//            if(roommanagerdaytimet != null)
//                roommanagerdaytimets.add(roommanagerdaytimet);
//        }
//        String date = RoomManagerNighttimeT.tohtml(roommanagerdaytimets);
//        //返回Html
//        return date;
//    }
//
//    public static String deletedtf(String roomtype){
//        String id = session.get("hotelid");
//        RoomTypeNighttime room = RoomTypeNighttime.find("byRoomtypeAndHotel_id",roomtype,id).first();
//        RoomManagerNighttimeF roommanagerdaytimeF = RoomManagerNighttimeF.find("byRoomtypenighttime_id",room.id).first();
//        roommanagerdaytimeF.delete();
//        //返回Html
//        return "delete success!";
//    }
//
//    public static String deletedtt(String roomtype){
//        String id = session.get("hotelid");
//        RoomTypeNighttime room = RoomTypeNighttime.find("byRoomtypeAndHotel_id",roomtype,id).first();
//        RoomManagerNighttimeT roommanagerdaytimet = RoomManagerNighttimeT.find("byRoomtypenighttime_id",room.id).first();
//        roommanagerdaytimet.delete();
//        //返回Html
//        return "delete success!";
//    }
}
