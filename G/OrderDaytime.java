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
 * Time: 下午5:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Torderdaytime")
public class OrderDaytime extends GenericModel{
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
    public String roomnumber;//房间余量

    @Required
    public String day;//所住天数

    @Required
    public String totalprice;//总价

    @Required
    public String state;//状态

    @Required
    public String scheduledtime;//预定时间

    @Required
    public String departuretime;//停售时间

    @Required
    public String retentiontime;//房间保留时间

    @Required
    public String contact;//联系方式

    @Required
    public String note;//备注

    //style="display:none;" 隐藏属性
    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td>"+this.id+"</td>");
        html.append("<td>"+this.fullname+"</td>");
        html.append("<td>"+this.roomtype+"</td>");
        html.append("<td>"+this.roomnumber+"</td>");
        html.append("<td >"+this.day+"</td>");
        html.append("<td >"+this.totalprice+"</td>");
        html.append("<td >"+this.state+"</td>");
        html.append("<td style=\"display:none;\">"+this.scheduledtime+"</td>");
        html.append("<td style=\"display:none;\">"+this.departuretime+"</td>");
        html.append("<td style=\"display:none;\">"+this.retentiontime+"</td>");
        html.append("<td style=\"display:none;\">"+this.contact+"</td>");
        html.append("<td style=\"display:none;\">"+this.note+"</td>");
        html.append("<td><a class=\"edit\" href=\"javascript:;\">已入住</a></td>");
        html.append("<td><a class=\"delete\" href=\"javascript:;\">未入住</a></td>");
        html.append("</tr>");
    }

    public static String tohtml(List<OrderDaytime> orderdt){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<orderdt.size();i++)
            orderdt.get(i).toTd(html);

        return html.toString();
    }
}
