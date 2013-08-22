package controllers;

import com.ning.http.client.Response;

import play.*;
import play.mvc.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void login() {
        render();
    }

    public static void personal(){
        render();
    }

    public static void statistical(){
        render();
    }

    public static void usermanager(){
        render();
    }

    public static void ordermanager(){
        render();
    }

    public static void nightordermanager(){
        render();
    }

    public static void remainroom(){
        render();
    }

    public static void hotel(){
        render();
    }

    public static void updateinformation(){
        render();
    }

    public static void logs(){
        render();
    }

    public static void error_404(){
        render();
    }

    public static void orderedit(){
        render();
    }

    public static void roommanager(){
        render();
    }

    public static void daytimeroom(){
        render();
    }

    public static void nighttimeroom(){
        render();
    }

    public static void nightroommanager(){
        render();
    }

    public static String ajax(){
        TestAjax ajaxob = TestAjax.find("byId", "1").first();
        String msg = ajaxob.msgnum;
        return msg;
    }

    public static String add(){
        TestAjax ajaxob = TestAjax.find("byId", "1").first();
        int msgnum = Integer.parseInt(ajaxob.msgnum)+1;

        ajaxob.setmsgnum(Integer.toString(msgnum));
        ajaxob.save();
        
        String msg = "success";
        return msg;
    }
}
