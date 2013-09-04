package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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

    @Required
    public String state;//状态

    public static String tostring(Date datetime){
        String ftime = null;
        if(datetime !=null){
            SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
            Date time = new Date();
            time = datetime;
            ftime = dateformat.format(time);
        }
        return ftime;
    }

    public static String todate(Date datetime){
        String ftime = null;
        if(datetime !=null){
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日");
            Date time = new Date();
            time = datetime;
            ftime = dateformat.format(time);
        }
        return ftime;
    }

    //style="display:none;" 隐藏属性
    public void toTd(StringBuffer html){
        String starttime = RoomManagerDaytime.tostring(this.starttime);
        String endtime = RoomManagerDaytime.tostring(this.endtime);
        String datetime = RoomManagerDaytime.todate(this.date);
        html.append("<tr class=\"odd\">");
        html.append("<td hidden>"+"</td>");
        html.append("<td>"+this.roomtype+"</td>");
        html.append("<td>"+datetime+"</td>");
        html.append("<td>"+starttime+"</td>");
        html.append("<td>"+endtime+"</td>");
        html.append("<td>"+this.roomnumber+"</td>");
        html.append("<td>"+this.roomprice+"</td>");
        html.append("<td  style=\"display:none;\">"+this.actualin+"</td>");
        html.append("<td><a id="+this.id+" data-toggle=\"modal\" href=\"#responsive\" class=\"update\">编辑</a></td>");
        if(this.state.equals("开售")){
            html.append("<td><a id="+this.id+" class=\"stop\" href=\"javascript:;\">停售</a></td>");
        }else{
            html.append("<td><a id="+this.id+" class=\"start\" href=\"javascript:;\">开售</a></td>");
        }
        html.append("</tr>");
    }

    public static String tohtml(List<RoomManagerDaytime> roommanagerdaytimefs){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<roommanagerdaytimefs.size();i++)
            roommanagerdaytimefs.get(i).toTd(html);

        return html.toString();
    }
}
