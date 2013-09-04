package controllers.manager;

import com.google.gson.Gson;
import models.RoomManagerDaytime;
import models.RoomTypeDaytime;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Date;
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

    public static String selectdtf(){
        String id = session.get("hotelid");
        //找到当前酒店的所有日间房型
        List<RoomTypeDaytime> roomtypedaytimes = RoomTypeDaytime.find("byHotel_idAndState",id,"审核通过").fetch();
        List<RoomManagerDaytime> roommanagerdaytimes = new ArrayList<RoomManagerDaytime>();
        Date today = new Date();
        today = new Date(today.getYear(),today.getMonth(), today.getDate());
        //
        createrminfo(roomtypedaytimes);
        //遍历所有日间房型
        for (Iterator i = roomtypedaytimes.iterator(); i.hasNext();){
            //获取日间房型对象
            RoomTypeDaytime roomtypedaytime = (RoomTypeDaytime) i.next();
            //根据日间房型对应的ID号，并依据房型的ID号找到对应的余房对象
            List<RoomManagerDaytime> roommanagerdaytime = RoomManagerDaytime.find("Roomtypedaytime_id=? and  Date>=? order by roomtype,date",roomtypedaytime.id,today).fetch();
            for(int j=0;j<roommanagerdaytime.size();j++){
                //将所找到的余房对象加入到日间房余房的对象列表中
                roommanagerdaytimes.add(roommanagerdaytime.get(j));
            }
        }
        String date = RoomManagerDaytime.tohtml(roommanagerdaytimes);
        //返回Html
        return date;
    }

    public static void createrminfo(List<RoomTypeDaytime> roomtdtime){
        List<RoomManagerDaytime> roommdts = new ArrayList<RoomManagerDaytime>();
        Date today = new Date();
        today = new Date(today.getYear(),today.getMonth(), today.getDate());

        for(int i=0;i<roomtdtime.size();i++){
            roommdts = RoomManagerDaytime.find("Roomtypedaytime_id=? and Date>=?",roomtdtime.get(i).id, today).fetch();
            if(roommdts.size()!=7){
                Date date=new Date();
                date=new Date(date.getYear(),date.getMonth(), date.getDate());
                for (int time =1;time<=7;time++){
                    List<RoomManagerDaytime> roomManagerDaytimeList = RoomManagerDaytime.find("Roomtypedaytime_id=? and Date=?", roomtdtime.get(i).id, date).fetch();//寻找Roomtypedaytime_id和date对应的那一个余房管理项目，如果有多个则删除其他

                    if (roomManagerDaytimeList.isEmpty()){
                        RoomManagerDaytime roommdt=new RoomManagerDaytime();
                        roommdt.roomtypedaytime = roomtdtime.get(i);
                        roommdt.preroom = roomtdtime.get(i).preroom;
                        roommdt.roomnumber = roomtdtime.get(i).preroom;
                        roommdt.roomprice = roomtdtime.get(i).loveprice;
                        roommdt.roomtype = roomtdtime.get(i).roomtype;
                        roommdt.date = date;
                        roommdt.state = "开售";
                        roommdt.save();
                    }
                    else if (roomManagerDaytimeList.size()>1){
                        for (int j=1;j<roomManagerDaytimeList.size();j++)
                            roomManagerDaytimeList.get(j).delete();
                    }
                    Long myTime=(date.getTime()/1000)+60*60*24;
                    date.setTime(myTime * 1000);
                }
            }
        }
    }

    public static String stopsell(String roomid){
        RoomManagerDaytime roommdt = RoomManagerDaytime.find("byId",roomid).first();
        roommdt.state = "停售";
        roommdt.save();
        return roommdt.state+"成功停售！";
    }

    public static String startsell(String roomid){
        RoomManagerDaytime roommdt = RoomManagerDaytime.find("byId",roomid).first();
        roommdt.state = "开售";
        roommdt.save();
        return "成功开售！";
    }

    //日间房
    public static void selectupdateinf(String roommanagerdt){
        RoomManagerDaytime roommanagerdaytime = RoomManagerDaytime.find("byId",roommanagerdt).first();
        //返回json
        renderJSON (new Gson().toJson(roommanagerdaytime).toString());
    }

    //更新修改的开售和停售时间
    public static String updateinfo(String roommanagerdt,String starttime,String endtime,int roomnumber){

        RoomManagerDaytime roommanagerdaytime = RoomManagerDaytime.find("byId", roommanagerdt).first();
        Date sourcedate = roommanagerdaytime.date;

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

        roommanagerdaytime.roomnumber = roomnumber;
        roommanagerdaytime.starttime = startdate;
        roommanagerdaytime.endtime = enddate;
        roommanagerdaytime.save();

        String msg = "保存成功！";
        return msg;
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
