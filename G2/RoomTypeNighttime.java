package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-23
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TroomtypeNight")
//夜间房房型
public class RoomTypeNighttime extends GenericModel{

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @Required
    public String roomtype;

    @Required
    @ManyToOne
    public Hotel hotel;

    @Required
    public String bed;

    @Required
    public String breakfast;

    @Required
    public String network;

    @Required
    public String loveprice;

    @Required
    public String publicprice;

    @Required
    public String commissionrate;

    @Required
    public String fixedcommission;

    @Required
    public String state;

    @Required
    public String area;

    @Required
    public String floor;

    @Required
    public String bednumber;

    //style="display:none;" 隐藏属性
    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td>"+this.roomtype+"</td>");
        html.append("<td>"+this.bed+"</td>");
        html.append("<td>"+this.breakfast+"</td>");
        html.append("<td>"+this.network+"</td>");
        html.append("<td>"+this.loveprice+"</td>");
        html.append("<td >"+this.publicprice+"</td>");
        html.append("<td >"+this.commissionrate+"</td>");
        html.append("<td >"+this.fixedcommission+"</td>");
        html.append("<td >"+this.state+"</td>");
        html.append("<td style=\"display:none;\">"+this.area+"</td>");
        html.append("<td style=\"display:none;\">"+this.floor+"</td>");
        html.append("<td style=\"display:none;\">"+this.bednumber+"</td>");

        html.append("<td><a data-toggle=\"modal\" href=\"#stack1\" class=\"edit\" href=\"javascript:;\">修改</a></td>");
        html.append("<td><a class=\"delete\" href=\"javascript:;\">删除</a></td>");
        html.append("</tr>");
    }

    public static String tohtml(List<RoomTypeNighttime> roomtypenighttimes){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<roomtypenighttimes.size();i++)
            roomtypenighttimes.get(i).toTd(html);

        return html.toString();
    }
}
