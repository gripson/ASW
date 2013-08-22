package controllers;

import models.User;
import play.data.validation.Valid;
import play.mvc.Before;
import play.mvc.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-19
 * Time: 下午3:31
 * To change this template use File | Settings | File Templates.
 */
public class UserControl extends Controller{

    @Before
    static void addUser() {
        User user = connected();
        if(user != null) {
            renderArgs.put("user", user);
        }
    }

    static User connected() {
        if(renderArgs.get("user") != null) {
            return renderArgs.get("user", User.class);
        }
        String username = session.get("user");
        if(username != null) {
            return User.find("byUsername", username).first();
        }
        return null;
    }

    // ~~

    public static void index() {
        if(connected() != null) {
            Application.index();
        }
        Application.login();
    }


    public static void saveUser(@Valid User user, String verifyPassword) {
        validation.required(verifyPassword);
        validation.equals(verifyPassword, user.password).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render("@register", user, verifyPassword);
        }
        user.create();
        session.put("user", user.userName);
        flash.success("Welcome, " + user.userName);
        Application.index();
    }

    public static void login(String Username, String password) {
        User user = User.find("byUsernameAndPassword", Username, password).first();
        if(user != null) {
            session.put("user", user.userName);
            flash.success("Welcome, " + user.userName);
            Application.index();
        }
        // Oops
        flash.put("username", Username);
        flash.error("Login failed");
        Application.login();
    }

    public static void logout() {
        session.clear();
        index();
    }

}
