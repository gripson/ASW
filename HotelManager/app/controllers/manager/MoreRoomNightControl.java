package controllers.manager;

import com.google.gson.Gson;
import models.*;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Date;
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
        public static String selectnt(){
            String id = session.get("hotelid");
            //找到当前酒店的所有日间房型
            List<RoomTypeNighttime> roomtypenighttimes = RoomTypeNighttime.find("byHotel_id",id).fetch();
            List<RoomManagerNighttime> roommanagernighttimes = new ArrayList<RoomManagerNighttime>();
            createrminfo(roomtypenighttimes);
            //遍历所有日间房型
            for (Iterator i = roomtypenighttimes.iterator(); i.hasNext();){
                //获取日间房型对象
                RoomTypeNighttime roomtypenighttime = (RoomTypeNighttime) i.next();
                //根据日间房型对应的ID号，并依据房型的ID号找到对应的余房对象
                List<RoomManagerNighttime> roommanagernighttime = RoomManagerNighttime.find("byRoomtypenighttime_id",roomtypenighttime.id).fetch();
                for(int j=0;j<roommanagernighttime.size();j++){
                    //将所找到的余房对象加入到日间房余房的对象列表中
                    roommanagernighttimes.add(roommanagernighttime.get(j));
                }
            }
            String date = RoomManagerNighttime.tohtml(roommanagernighttimes);
            //返回Html
            return date;
        }

        public static void createrminfo(List<RoomTypeNighttime> roomtntime){
            List<RoomManagerNighttime> roommnts = null;
            Date date = new Date();
            for(int i=0;i<roomtntime.size();i++){
                if(roomtntime.get(i).state.equals("正在审核") || roomtntime.get(i).state.equals("已删除")){
                    roommnts = RoomManagerNighttime.find("byRoomtypenighttime_id",roomtntime.get(i).id).fetch();
                    if(!roommnts.isEmpty()){
                        for (int j =0;j<roommnts.size();j++)
                            roommnts.get(j).delete();
                    }
                }else if (roomtntime.get(i).state.equals("审核通过")){
                    roommnts = RoomManagerNighttime.find("byRoomtypenighttime_id",roomtntime.get(i).id).fetch();
                    if(roommnts.isEmpty()){
                        for (int time =1;time<=7;time++){
                            RoomManagerNighttime roommnt = new RoomManagerNighttime();
                            roommnt.roomtypenighttime = roomtntime.get(i);
                            roommnt.roomprice = roomtntime.get(i).loveprice;
                            roommnt.roomtype = roomtntime.get(i).roomtype;
                            roommnt.date = date;
                            roommnt.state = "开售";
                            roommnt.create();
                        }
                    }
                }
            }
        }

    public static String stopsell(String roomid){
        RoomManagerNighttime roommdt = RoomManagerNighttime.find("byId",roomid).first();
        roommdt.state = "停售";
        roommdt.save();
        return roommdt.state+"成功停售！";
    }

    public static String startsell(String roomid){
        RoomManagerNighttime roommdt = RoomManagerNighttime.find("byId",roomid).first();
        roommdt.state = "开售";
        roommdt.save();
        return "成功开售！";
    }

    //夜间房
    public static void selectupdateinf(String roommanagernt){
        RoomManagerNighttime roommanagernighttime = RoomManagerNighttime.find("byId",roommanagernt).first();
        //返回json
        renderJSON (new Gson().toJson(roommanagernighttime).toString());
    }

    //更新修改的开售和停售时间
    public static String updateinfo(String roommanagerdt,String starttime,String endtime,int roomnumber){

        RoomManagerNighttime roommanagernighttime = RoomManagerNighttime.find("byId", roommanagerdt).first();
        Date sourcedate = roommanagernighttime.date;

        Date startdate = new Date();

        String hours = starttime.substring(0,2);
        int ihours = Integer.parseInt(hours);
        String minutes = starttime.substring(3,5);
        int iminutes = Integer.parseInt(minutes);
        String seconds = starttime.substring(6,8);
        int iseconds = Integer.parseInt(seconds);
        startdate.setHours(ihours);
        startdate.setMinutes(iminutes);
        startdate.setSeconds(iseconds);

        Date enddate = new Date();

        String ehours = endtime.substring(0,2);
        int iehours = Integer.parseInt(ehours);
        String eminutes = endtime.substring(3,5);
        int ieminutes = Integer.parseInt(eminutes);
        String eseconds = endtime.substring(6,8);
        int ieseconds = Integer.parseInt(eseconds);
        enddate.setHours(iehours);
        enddate.setMinutes(ieminutes);
        enddate.setSeconds(ieseconds);


        roommanagernighttime.starttime = startdate;
        roommanagernighttime.endtime = enddate;
        roommanagernighttime.save();

        String msg = "保存成功！";
        return msg;
    }
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