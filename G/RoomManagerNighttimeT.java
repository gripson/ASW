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
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TroommanagernighttimeT")
//夜间房周五至周六余房
public class RoomManagerNighttimeT extends GenericModel {
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
    public String starttime;

    @Required
    public String endtime;

    @Required
    public String roomnumber;

    @Required
    public String roomprice;

    //style="display:none;" 隐藏属性
    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td>"+this.roomtype+"</td>");
        html.append("<td>"+this.starttime+"</td>");
        html.append("<td>"+this.endtime+"</td>");
//        html.append("<td>"+this.roomnumber+"</td>");
        html.append("<td>"+this.roomprice+"</td>");

        html.append("<td><a class=\"edit\" href=\"javascript:;\">修改</a></td>");
        html.append("<td><a id="+this.roomtype+" class=\"delete\" href=\"javascript:;\">删除</a></td>");
        html.append("</tr>");
    }

    public static String tohtml(List<RoomManagerNighttimeT> roommanagerdaytimets){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<roommanagerdaytimets.size();i++)
            roommanagerdaytimets.get(i).toTd(html);

        return html.toString();
    }
}
