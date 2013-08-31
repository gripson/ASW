package controllers.manager;

import models.OrderDaytime;
import models.OrderNighttime;
import models.Statistical;
import play.mvc.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-24
 * Time: 下午10:57
 * To change this template use File | Settings | File Templates.
 */
public class OrderControl extends Controller {
    public static String select(){
        String id = session.get("hotelid");
        List<OrderDaytime> orderdt = OrderDaytime.find("byHotel_id",id).fetch();
        String date = OrderDaytime.tohtml(orderdt);
        //返回Html
        return date;
    }

    public static String savein(String orderdtid){
        OrderDaytime orderdt = OrderDaytime.find("byId",orderdtid).first();
        orderdt.state = "已入住";
        orderdt.save();
        Statistical statistical = new Statistical();
        statistical.checkintime = orderdt.checkintime;
        statistical.contact = orderdt.contact;
        statistical.orderdt_id = orderdt.id;
        statistical.hotel = orderdt.hotel;
        statistical.fullname = orderdt.fullname;
        statistical.roomtype = orderdt.roomtype;
        statistical.day = orderdt.day;
        statistical.totalprice = orderdt.totalprice;
        statistical.checkintime = orderdt.checkintime;
        statistical.scheduledtime = orderdt.scheduledtime;
        statistical.departuretime = orderdt.departuretime;
        statistical.retentiontime = orderdt.retentiontime;
        statistical.contact = orderdt.contact;
        statistical.note = orderdt.note;
        statistical.state = orderdt.state;
        statistical.save();
        orderdt.delete();
        return "成功修改！";
    }

    public static String saveout(String orderdtid){
        OrderDaytime orderdt = OrderDaytime.find("byId",orderdtid).first();
        orderdt.state = "未入住";
        orderdt.save();
        Statistical statistical = new Statistical();
        statistical.checkintime = orderdt.checkintime;
        statistical.contact = orderdt.contact;
        statistical.orderdt_id = orderdt.id;
        statistical.hotel = orderdt.hotel;
        statistical.fullname = orderdt.fullname;
        statistical.roomtype = orderdt.roomtype;
        statistical.day = orderdt.day;
        statistical.totalprice = orderdt.totalprice;
        statistical.checkintime = orderdt.checkintime;
        statistical.scheduledtime = orderdt.scheduledtime;
        statistical.departuretime = orderdt.departuretime;
        statistical.retentiontime = orderdt.retentiontime;
        statistical.contact = orderdt.contact;
        statistical.note = orderdt.note;
        statistical.state = orderdt.state;

        statistical.save();
        orderdt.delete();
        return "成功修改！";
    }
//夜间房订单处理
    public static String selectnight(){
        String id = session.get("hotelid");
        List<OrderNighttime> orderdt = OrderNighttime.find("byHotel_id",id).fetch();
        String date = OrderNighttime.tohtml(orderdt);
        //返回Html
        return date;
    }

    public static String nightsavefull(String orderdtid){
        OrderNighttime orderdt = OrderNighttime.find("byId",orderdtid).first();
        orderdt.state = "客满";
        orderdt.save();
        Statistical statistical = new Statistical();
        statistical.checkintime = orderdt.checkintime;
        statistical.contact = orderdt.contact;
        statistical.orderdt_id = orderdt.id;
        statistical.hotel = orderdt.hotel;
        statistical.fullname = orderdt.fullname;
        statistical.roomtype = orderdt.roomtype;
        statistical.totalprice = orderdt.totalprice;
        statistical.checkintime = orderdt.checkintime;
        statistical.scheduledtime = orderdt.scheduledtime;
        statistical.departuretime = orderdt.departuretime;
        statistical.retentiontime = orderdt.retentiontime;
        statistical.contact = orderdt.contact;
        statistical.note = orderdt.note;
        statistical.state = orderdt.state;
        statistical.save();
        orderdt.delete();
        return "成功修改！";
    }

    public static String nightsaveempty(String orderdtid){
        OrderNighttime orderdt = OrderNighttime.find("byId",orderdtid).first();
        orderdt.state = "可入住";
        orderdt.save();
        Statistical statistical = new Statistical();
        statistical.checkintime = orderdt.checkintime;
        statistical.contact = orderdt.contact;
        statistical.orderdt_id = orderdt.id;
        statistical.hotel = orderdt.hotel;
        statistical.fullname = orderdt.fullname;
        statistical.roomtype = orderdt.roomtype;
        statistical.totalprice = orderdt.totalprice;
        statistical.checkintime = orderdt.checkintime;
        statistical.scheduledtime = orderdt.scheduledtime;
        statistical.departuretime = orderdt.departuretime;
        statistical.retentiontime = orderdt.retentiontime;
        statistical.contact = orderdt.contact;
        statistical.note = orderdt.note;
        statistical.state = orderdt.state;
        statistical.save();
        return "成功修改！";
    }

    public static String nightsavein(String orderdtid){
        OrderNighttime orderdt = OrderNighttime.find("byId",orderdtid).first();
        orderdt.state = "已入住";
        orderdt.save();
        Statistical statistical = new Statistical();
        statistical.checkintime = orderdt.checkintime;
        statistical.contact = orderdt.contact;
        statistical.orderdt_id = orderdt.id;
        statistical.hotel = orderdt.hotel;
        statistical.fullname = orderdt.fullname;
        statistical.roomtype = orderdt.roomtype;
        statistical.totalprice = orderdt.totalprice;
        statistical.checkintime = orderdt.checkintime;
        statistical.scheduledtime = orderdt.scheduledtime;
        statistical.departuretime = orderdt.departuretime;
        statistical.retentiontime = orderdt.retentiontime;
        statistical.contact = orderdt.contact;
        statistical.note = orderdt.note;
        statistical.state = orderdt.state;
        statistical.save();
        orderdt.delete();
        return "成功修改！";
    }

    public static String nightsaveout(String orderdtid){
        OrderNighttime orderdt = OrderNighttime.find("byId",orderdtid).first();
        orderdt.state = "未入住";
        orderdt.save();
        Statistical statistical = new Statistical();
        statistical.checkintime = orderdt.checkintime;
        statistical.contact = orderdt.contact;
        statistical.orderdt_id = orderdt.id;
        statistical.hotel = orderdt.hotel;
        statistical.fullname = orderdt.fullname;
        statistical.roomtype = orderdt.roomtype;
        statistical.totalprice = orderdt.totalprice;
        statistical.checkintime = orderdt.checkintime;
        statistical.scheduledtime = orderdt.scheduledtime;
        statistical.departuretime = orderdt.departuretime;
        statistical.retentiontime = orderdt.retentiontime;
        statistical.contact = orderdt.contact;
        statistical.note = orderdt.note;
        statistical.state = orderdt.state;

        statistical.save();
        orderdt.delete();
        return "成功修改！";
    }
}
