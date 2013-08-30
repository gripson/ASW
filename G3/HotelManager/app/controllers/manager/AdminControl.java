package controllers.manager;

import com.google.gson.Gson;
import models.Admin;
import models.Hotel;
import play.data.validation.Valid;
import play.mvc.Before;
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

    public static String update(String financialphone,String financialname,String fax,String frontcontact,String useremail,String telephone,String phone, String password,String email,String permissions,String fullname){

        String username = session.get("admin");
        Admin admin = Admin.find("byUsername", username).first();

        if(!password.equals("")){
            admin.password=password;
        }
        admin.email=email;
        admin.permissions=permissions;
        admin.fullname=fullname;
        admin.phone = phone;
        admin.telephone = telephone;
        admin.useremail = useremail;
        admin.frontcontact = frontcontact;
        admin.fax = fax;
        admin.financialname = financialname;
        admin.financialphone = financialphone;
        admin.save();

        String msg="修改成功！";

        return msg;
    }

    public static void selectinfo(){
        String username = session.get("admin");
        Admin admin = Admin.find("byUsername", username).first();

        renderJSON(new Gson().toJson(admin).toString());
    }

    //酒店后台用户管理模块——系统管理员所需的方法
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
