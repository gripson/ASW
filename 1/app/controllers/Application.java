package controllers;

import models.User;
import play.mvc.*;

import java.util.*;

import models.*;

import static controllers.HotelControl.*;

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

    public static void index() {
        render();
    }

    public static void hotel(int sort, int start) {
        List<Hotel> results = loadHotel(sort, start, 10);
        render(results);
    }

//    public static  void room(int name,Date date){}

    public static void room(String id, String date) {
        Date startDate = null;
        List<Room> rooms = Room.find("hotel.id", id).fetch();
        if (date == null) {
            Calendar cal = Calendar.getInstance();
            startDate = cal.getTime();
        } else {
            long sdate = Long.parseLong(date);
            startDate = new Date(sdate);
        }

        startDate = new Date(startDate.getYear(), startDate.getMonth(), startDate.getDate());
        List<RoomDate> results = new ArrayList<RoomDate>();
        results.clear();
        for (Room r : rooms) {
            RoomDate roomDate = RoomDate.find("byRoomAndDate", r, startDate).first();
            if (roomDate != null)
                results.add(roomDate);
        }

        Hotel hotel = Hotel.findById(id);

        Favor favor = Favor.find("byHotelAndUser", hotel, connected()).first();
        boolean isFavor;
        if (favor != null)
            isFavor = true;
        else
            isFavor = false;

        render(results, hotel, startDate, isFavor);
    }

    public static void order(String roomId, Date startDate, int days) {
        Order order = new Order();
        order.date = startDate;
        order.days = days;
        order.name = session.get("user");
        order.phoneNum = session.get("phoneNum");
        RoomDate roomDate = RoomDate.find("room.id", roomId).first();
        Hotel hotel = Hotel.find("id", roomDate.room.hotel.id).first();
        order.room = roomDate.room;
        order.hotel = hotel;
        order.money = days * roomDate.roompriceNow;
        render(order);
    }

    public static void confirm(Order order, String name, String phoneNum, String remark) {
        //connected
        User user = Application.connected();
        order.user = user;
        order.name = name;
        order.phoneNum = phoneNum;
        order.remark = remark;
        //validation.valid(order);
        order.save();
        show(order);
    }

    public static void show(Order order) {
        render(order);
    }

    public static void cancel(Order order) {
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
}