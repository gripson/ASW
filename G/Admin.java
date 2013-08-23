package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-15
 * Time: 下午7:53
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Tadmin")
public class Admin extends GenericModel {
    @Id
    @Column(name="userName")
    @MaxSize(11)
    public String userName;

    @Required
    public String password;

    @Required
    public String email;

    @Required
    public String permissions;

    @Required
    public String registrationCode;

    //酒店负责人所填写的信息
    @Required
    public String fullname;

    @Required
    public String position;

    @Required
    public String phone;

    @Required
    public String telephone;

    @Required
    public String useremail;

    @Required
    public String frontcontact;

    @Required
    public String fax;

    @Required
    public String financialname;

    @Required
    public String financialphone;

    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td class=\"sorting_1\">"+this.userName+"</td>");
        html.append("<td class=\"\">"+this.password+"</td>");
        html.append("<td class=\"\">"+this.email+"</td>");
        html.append("<td class=\"center \">"+this.permissions+"</td>");
        html.append("<td class=\"\">"+this.registrationCode+"</td>");
        html.append("<td><a class=\"edit\" href=\"javascript:;\">修改</a></td>");
        html.append("<td><a class=\"delete\" href=\"javascript:;\">删除</a></td>");
        html.append("</tr>");
    }

    public static String tohtml(List<Admin> admins){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<admins.size();i++)
            admins.get(i).toTd(html);

        return html.toString();

    }

}
