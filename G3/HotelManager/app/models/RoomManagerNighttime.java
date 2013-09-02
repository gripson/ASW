package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-23
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Troommanagernighttime")
//夜间房管理
public class RoomManagerNighttime extends GenericModel{
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @Required
    @ManyToOne
    public RoomTypeNighttime roomtypenighttime;

    @Required
    public String roomtype;

    @Required
    public Date date;

    @Required
    public Date starttime;

    @Required
    public Date endtime;

    @Required
    public String state;

//    @Required
//    public String roomnumber;

    @Required
    public double roomprice;

    public static String tostring(Date nighttime){
        String ftime = null;
        if(nighttime !=null){
            SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
            Date time = new Date();
            time = nighttime;
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
        String starttime = RoomManagerNighttime.tostring(this.starttime);
        String endtime = RoomManagerNighttime.tostring(this.endtime);
        String datetime = RoomManagerNighttime.todate(this.date);
        html.append("<tr class=\"odd\">");
        html.append("<td>"+this.roomtype+"</td>");
        html.append("<td>"+datetime+"</td>");
        html.append("<td>"+starttime+"</td>");
        html.append("<td>"+endtime+"</td>");
        html.append("<td>"+this.roomprice+"</td>");
        html.append("<td><a id="+this.id+" data-toggle=\"modal\" href=\"#responsive\" class=\"update\">编辑</a></td>");
        if(this.state.equals("开售")){
            html.append("<td><a id="+this.id+" class=\"stop\" href=\"javascript:;\">停售</a></td>");
        }else{
            html.append("<td><a id="+this.id+" class=\"start\" href=\"javascript:;\">开售</a></td>");
        }
        html.append("</tr>");
    }

    public static String tohtml(List<RoomManagerNighttime> roomManagerNighttimes){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<roomManagerNighttimes.size();i++)
            roomManagerNighttimes.get(i).toTd(html);

        return html.toString();
    }
}
