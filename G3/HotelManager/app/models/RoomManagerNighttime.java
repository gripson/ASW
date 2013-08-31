package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
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

    //style="display:none;" 隐藏属性
    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td>"+this.roomtype+"</td>");
        html.append("<td>"+this.date+"</td>");
        html.append("<td>"+this.starttime+"</td>");
        html.append("<td>"+this.endtime+"</td>");
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
