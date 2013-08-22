package controllers;

import models.Favor;
import models.Hotel;
import models.User;
import play.mvc.Controller;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Pinjie
 * Date: 13-7-17
 * Time: 下午8:37
 * To change this template use File | Settings | File Templates.
 */
public class RoomControl extends Controller{

    public static void favorite(String id){
        Hotel hotel=Hotel.find("id",id).first();
        User user=Application.connected();
        Favor favor=new Favor();
        favor.hotel=hotel;
        favor.user=user;
        favor.save();
        String date="";
        Application.room(id,date);
    }
}
