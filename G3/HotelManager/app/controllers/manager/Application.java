package controllers.manager;

import models.Admin;
import models.TestAjax;
import play.mvc.Before;
import play.mvc.Controller;

public class Application extends Controller {

    @Before
    static void addUser() {
        Admin admin = connected();
        if(admin != null) {
            renderArgs.put("admin", admin);
        }
    }

    static Admin connected() {
        if(renderArgs.get("admin") != null) {
            return renderArgs.get("admin", Admin.class);
        }
        String username = session.get("admin");
        if(username != null) {
            return Admin.find("byUsername", username).first();
        }
        return null;
    }

    // ~~

    public static void index() {
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void saveUser(String username,String password,String email, String registration_code, String rpassword) {
        Admin admin = new Admin();
        admin.userName = username;
        admin.password = password;
        admin.email = email;
        admin.registrationCode = registration_code;
        if(password.equals(rpassword)){
            admin.create();
            session.put("admin", admin.userName);
            flash.success("Welcome, " + admin.userName);
            Application.index();
        }
        Application.login();
    }

    public static void logincheck(String username, String password) {
        Admin user = Admin.find("byUsernameAndPassword", username, password).first();
        if(user != null) {
            session.put("admin", user.userName);
            if(user.hotel != null){
                session.put("hotelid",user.hotel.id);
            }
            //System.out.println(user.hotel);
            flash.success("Welcome, " + user.userName);
            Application.index();
        }
        // Oops
        flash.put("username", username);
        flash.error("Login failed");
        login();
    }

    public static void logout() {
        session.clear();
        index();
    }

//    public static void index() {
//        render();
//    }

    public static void login() {
        render();
    }

    public static void personal(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void statistical(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void usermanager(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void ordermanager(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void nightordermanager(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void remainroom(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void hotel(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void updateinformation(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void logs(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void error_404(){
        render();
    }

    public static void orderedit(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void roommanager(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void daytimeroom(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void nighttimeroom(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void nightroommanager(){
        if(connected() != null) {
            render();
        }
        login();
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
