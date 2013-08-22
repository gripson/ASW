package controllers;

import models.Admin;
import play.mvc.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-15
 * Time: 下午7:33
 * To change this template use File | Settings | File Templates.
 */
public class AdminControl extends Controller {

    public static String save(String userName, String hotelName,String email,String permissions,String registrationCode){

        Admin admin= new Admin();

        Admin user = Admin.find("byUsername", userName).first();

        if( user == null){
            admin.userName=userName;
            admin.hotelName=hotelName;
            admin.email=email;
            admin.permissions=permissions;
            admin.registrationCode=registrationCode;
            admin.create();
        }
        else{
            user.hotelName=hotelName;
            user.email=email;
            user.permissions=permissions;
            user.registrationCode=registrationCode;
            user.save();
        }

        String msg="修改成功！";

        return msg;
    }

    public static String select(){
        List<Admin> admins = Admin.findAll();
        String date = Admin.tohtml(admins);
        //
        return date;
    }

    public static String delete(String userName){
        Admin admin= (Admin) Admin.find("byUsername",userName).first();

        admin.delete();
        String msg="修改成功！";

        return msg;
    }
}
