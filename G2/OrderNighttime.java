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
 * Time: 下午7:53
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Tordernighttime")
public class OrderNighttime extends GenericModel{
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @Required
    @ManyToOne
    public Hotel hotel;

    @Required
    public String fullname;//顾客姓名

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
    public String scheduledtime;

    @Required
    public String departuretime;

    @Required
    public String retentiontime;

    @Required
    public String contact;

    @Required
    public String note;

    //style="display:none;" 隐藏属性
    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td>"+this.id+"</td>");
        html.append("<td>"+this.fullname+"</td>");
        html.append("<td>"+this.roomtype+"</td>");
        html.append("<td>"+this.roomnumber+"</td>");
//      html.append("<td >"+this.day+"</td>");
        html.append("<td >"+this.totalprice+"</td>");
        html.append("<td >"+this.state+"</td>");
        html.append("<td style=\"display:none;\">"+this.scheduledtime+"</td>");
        html.append("<td style=\"display:none;\">"+this.departuretime+"</td>");
        html.append("<td style=\"display:none;\">"+this.retentiontime+"</td>");
        html.append("<td style=\"display:none;\">"+this.contact+"</td>");
        html.append("<td style=\"display:none;\">"+this.note+"</td>");
        html.append("<td><a class=\"edit\" href=\"javascript:;\">客满</a></td>");
        html.append("<td><a class=\"edit\" href=\"javascript:;\">可入住</a></td>");
        html.append("<td><a class=\"edit\" href=\"javascript:;\">已入住</a></td>");
        html.append("<td><a class=\"delete\" href=\"javascript:;\">未入住</a></td>");
        html.append("</tr>");
    }

    public static String tohtml(List<OrderNighttime> orderdt){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<orderdt.size();i++)
            orderdt.get(i).toTd(html);

        return html.toString();
    }
}
