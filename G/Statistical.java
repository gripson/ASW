package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-23
 * Time: 下午7:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Tstatistical")
//账单统计
public class Statistical extends GenericModel {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @Required
    @ManyToOne
    public Hotel hotel;

    @Required
    public String fullname;

    @Required
    public String roomtype;

    @Required
    public String roomnumber;

    @Required
    public String day;

    @Required
    public String totalprice;

    @Required
    public String state;

    @Required
    public String fixedcommission;

    @Required
    public String commissionrate;

    @Required
    public String scheduledtime;//预定时间

    @Required
    public String departuretime;//离店时间

    @Required
    public String retentiontime;//房间保留时间

    @Required
    public String contact;

    @Required
    public String note;

    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td class=\"sorting_1\">"+this.id+"</td>");
        html.append("<td class=\"\">"+this.fullname+"</td>");
        html.append("<td class=\"\">"+this.hotel+"</td>");
        html.append("<td class=\"center \">"+this.roomnumber+"</td>");
        html.append("<td class=\"\">"+this.day+"</td>");
        html.append("<td class=\"\">"+this.day+"</td>");
        html.append("<td class=\"\">"+this.day+"</td>");
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
