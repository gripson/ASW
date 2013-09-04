package controllers.frontend;

import models.*;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Application extends Controller {

    static User connected() {
        if (renderArgs.get("user") != null) {
            return renderArgs.get("user", User.class);
        }
        String phoneNum = session.get("phoneNum");
        if (phoneNum != null) {
            return User.find("byPhoneNum", phoneNum).first();
        }
        return null;
    }

    static boolean checkroom(Date startdate,int days,String roomId){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(startdate);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date date=new Date();

        for (int i=0;i<days;i++)
        {
            date=calendar.getTime();
            RoomManagerDaytime roomDate=RoomManagerDaytime.find("byDateAndRoom.id",date,roomId).first();
            if (roomDate.roomnumber==0)
                return true;//满房
            calendar.add(Calendar.DAY_OF_YEAR,1);
        }

        return false;
    }

    public static void index() {
        render();
    }

    public static void hotel(int sort, int start) {
        List<Hotel> results = HotelControl.loadHotel(sort, start, 10);
        render(results);
    }

//    public static  void room(int name,Date date){}

    public static void room(String id, String date,int days) {
        //判断日期
        Date startDate = null;
        if (date == null) {
            Calendar cal = Calendar.getInstance();
            startDate = cal.getTime();
        } else {
            long sdate = Long.parseLong(date);
            startDate = new Date(sdate);
        }

        List<RoomTypeDaytime> rooms = RoomTypeDaytime.find("hotel.id", id).fetch();
        startDate = new Date(startDate.getYear(), startDate.getMonth(), startDate.getDate());
        List<RoomManagerDaytime> roomDates = new ArrayList<RoomManagerDaytime>();
        roomDates.clear();
        for (RoomTypeDaytime r : rooms) {
            RoomManagerDaytime roomDate = RoomManagerDaytime.find("byRoomAndDate", r, startDate).first();
            if (roomDate != null)
                roomDates.add(roomDate);
        }

        Hotel hotel = Hotel.findById(id);

        Favor favor = Favor.find("byHotelAndUser", hotel, connected()).first();
        boolean isFavor;
        if (favor != null)
            isFavor = true;
        else
            isFavor = false;

        render(roomDates, hotel, startDate, isFavor);
    }

    public static void order(String roomId, Date startDate, int days) {
        OrderDaytime order = new OrderDaytime();
        order.checkintime = startDate;
        order.day = days;
        order.fullname = session.get("user");
        order.contact = session.get("phoneNum");
        //RoomTypeDaytime roomDate = RoomTypeDaytime.find("room.id", roomId).first();
        RoomManagerDaytime roomManagerDaytime=RoomManagerDaytime.find("id",roomId).first();
        Hotel hotel = Hotel.find("id", roomManagerDaytime.roomtypedaytime.hotel.id).first();
        order.roomManagerDaytime = roomManagerDaytime;
        order.hotel = hotel;
        order.totalprice = days * roomManagerDaytime.roomprice;
        render(order);
    }

    public static void confirm(OrderDaytime order, String name, String phoneNum, String remark) {
        //connected
        User user = Application.connected();
        order.user = user;
        order.fullname = name;
        order.contact = phoneNum;
        order.note = remark;
        //validation.valid(order);
        order.save();
        show(order);
    }

    public static void show(OrderDaytime order) {
        render(order);
    }

    public static void cancel(OrderDaytime order) {
        order.delete();
        render(order);
    }

    public static void myHome(int select) {
        User user = connected();
        boolean a[] = new boolean[4];
        a[select] = true;
        List<Favor> favors = Favor.find("byUser", connected()).fetch();
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        for (Favor f : favors) {
            hotels.add(f.hotel);
        }

        //未成功订单


        //成功订单


        render(user, a, hotels);
    }

    public static void test() {
        render();
    }

    public static void login(String phonenum, String password) {
        User user = User.find("byPhoneNumAndPassword", phonenum, password).first();
        if (user != null) {
            session.put("phoneNum", user.phoneNum);
            session.put("user", user.userName);
        }
        // Oops
        renderJSON(user.phoneNum);
        //hotel(0,0);
    }

    public static void logout() {
        session.clear();
        hotel(0, 0);
    }

    public static void signUp(String phonenum,String password,String username){
        User user= User.find("byPhoneNUM",phonenum).first();
        if (user==null)
        {
        user=new User();
        user.phoneNum=phonenum;
        user.userName=username;
        user.password=password;
        user.save();
        }

        User newuser = User.find("byPhoneNumAndPassword", phonenum, password).first();
        if (newuser != null) {
            session.put("phoneNum", newuser.phoneNum);
            session.put("user", newuser.userName);
        }
        renderJSON(newuser.phoneNum);
    }

//    public static void autoAddRomm(){
//        //获取当前日期
//        Date today=new Date();
//        today = new Date(today.getYear(),today.getMonth(), today.getDate());
//
//        //遍历数据库每个日期；
//        Long myTime=(today.getTime()/1000)+60*60*24;
//        Date myDate=today;
//        for (int i=0;i<7;i++)
//        {
//            
//
//        }
//    }

}