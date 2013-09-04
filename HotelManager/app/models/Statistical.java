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

//    @Required
//    public String roomnumber;

    @Required
    public int day;

    @Required
    public double totalprice;

    @Required
    public String state;

    @Required
    public double fixedcommission;

    @Required
    public double commissionrate;

    @Required
    public Date scheduledtime;//预定时间

    @Required
    public Date checkintime;//入住时间

    @Required
    public Date departuretime;//离店时间

    @Required
    public String retentiontime;//房间保留时间

    @Required
    public String contact;

    @Required
    public String note;

    @Required
    public String orderdt_id;

    @Required
    public String ordernt_id;

    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td class=\"sorting_1\">"+this.id+"</td>");
        html.append("<td class=\"\">"+this.fullname+"</td>");
        html.append("<td class=\"\">"+this.roomtype+"</td>");
        html.append("<td class=\"\">"+this.day+"</td>");
        html.append("<td class=\"\">"+this.totalprice+"</td>");
        html.append("<td class=\"\">"+this.state+"</td>");
        html.append("<td class=\"\" style=\"display:none;\">"+this.scheduledtime+"</td>");
        html.append("<td class=\"\" style=\"display:none;\">"+this.checkintime+"</td>");
        html.append("<td class=\"\" style=\"display:none;\">"+this.departuretime+"</td>");
        html.append("<td class=\"\" style=\"display:none;\">"+this.retentiontime+"</td>");
        html.append("<td class=\"\" style=\"display:none;\">"+this.contact+"</td>");
        html.append("<td class=\"\" style=\"display:none;\">"+this.note+"</td>");
        html.append("</tr>");
    }

    public static String tohtml(List<Statistical> statisticals){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<statisticals.size();i++)
            statisticals.get(i).toTd(html);

        return html.toString();

    }
}
