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
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Troomtype")
//日间房房型管理
public class RoomTypeDaytime extends GenericModel {
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
    public String bed;//床规格

    @Required
    public String breakfast;//早餐

    @Required
    public String network;

    @Required
    public double loveprice;//房型的默认售价

    @Required
    public int preroom;//每日预留房间数量

    @Required
    public double publicprice;//门市价 目前没有用处

    @Required
    public double commissionrate;//佣金比率

    @Required
    public double fixedcommission;//固定佣金

    @Required
    public String state;//状态

    @Required
    public String area;//面积

    @Required
    public int floor;//楼层

    @Required
    public int bednumber;//床个数

    //style="display:none;" 隐藏属性
    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td>"+this.roomtype+"</td>");
        html.append("<td>"+this.bed+"</td>");
        html.append("<td>"+this.breakfast+"</td>");
        html.append("<td>"+this.network+"</td>");
        html.append("<td>"+this.loveprice+"</td>");
        html.append("<td >"+this.publicprice+"</td>");
        html.append("<td >"+this.preroom+"</td>");
        html.append("<td >"+this.commissionrate+"</td>");
        html.append("<td >"+this.fixedcommission+"</td>");
        html.append("<td >"+this.state+"</td>");
        html.append("<td style=\"display:none;\">"+this.area+"</td>");
        html.append("<td style=\"display:none;\">"+this.floor+"</td>");
        html.append("<td style=\"display:none;\">"+this.bednumber+"</td>");

        html.append("<td><a id="+this.roomtype+" data-toggle=\"modal\" href=\"#stack1\" class=\"edit\" >修改</a></td>");
        html.append("<td><a id="+this.roomtype+" class=\"delete\" >删除</a></td>");
        html.append("</tr>");
    }

    public static String tohtml(List<RoomTypeDaytime> roomtypedaytimes){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<roomtypedaytimes.size();i++)
            roomtypedaytimes.get(i).toTd(html);

        return html.toString();
    }
}
