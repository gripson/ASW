package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-23
 * Time: 下午5:13
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Troommanagerdaytime")
//日间房余房管理
public class RoomManagerDaytime extends GenericModel {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @Required
    @ManyToOne
    public RoomTypeDaytime roomtypedaytime;

    @Required
    public Date date;//日期

    @Required
    public String roomtype;//房型

    @Required
    public Date starttime;//开售时间

    @Required
    public Date endtime;//停售时间

    @Required
    public int roomnumber;//房间余量

    @Required
    public int preroom;//预留房间数

    @Required
    public int actualin;//实际入住

    @Required
    public double roomprice;//售价

    //style="display:none;" 隐藏属性
    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td>"+this.roomtype+"</td>");
        html.append("<td>"+this.date+"</td>");
        html.append("<td>"+this.starttime+"</td>");
        html.append("<td>"+this.endtime+"</td>");
        html.append("<td>"+this.preroom+"</td>");
        html.append("<td>"+this.roomnumber+"</td>");
        html.append("<td>"+this.roomprice+"</td>");
        html.append("<td  style=\"display:none;\">"+this.actualin+"</td>");
        html.append("<td><a class=\"edit\" href=\"javascript:;\">编辑</a></td>");
        html.append("<td><a id="+this.roomtype+" class=\"delete\" href=\"javascript:;\">停售</a></td>");
        html.append("</tr>");
    }

    public static String tohtml(List<RoomManagerDaytime> roommanagerdaytimefs){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<roommanagerdaytimefs.size();i++)
            roommanagerdaytimefs.get(i).toTd(html);

        return html.toString();
    }
}
