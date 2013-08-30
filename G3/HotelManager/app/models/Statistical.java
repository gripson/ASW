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

    @Required
    public String roomnumber;

    @Required
    public String day;

    @Required
    public String totalprice;

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
    public Date retentiontime;//房间保留时间

    @Required
    public String contact;

    @Required
    public String note;

    public void toTd(StringBuffer html){
        html.append("<tr class=\"odd\">");
        html.append("<td class=\"sorting_1\">"+this.id+"</td>");
        html.append("<td class=\"\">"+this.fullname+"</td>");
        html.append("<td class=\"\">"+this.roomtype+"</td>");
        html.append("<td class=\"\">"+this.totalprice+"</td>");
        html.append("<td class=\"\">"+this.fixedcommission+"</td>");
        html.append("<td class=\"\">"+this.commissionrate+"</td>");

        html.append("<td class=\"\">"+this.scheduledtime+"</td>");
        html.append("<td class=\"\">"+this.checkintime+"</td>");
        html.append("<td class=\"\">"+this.departuretime+"</td>");
        html.append("<td class=\"\">"+this.retentiontime+"</td>");
        html.append("<td class=\"\">"+this.contact+"</td>");
        html.append("<td class=\"\">"+this.state+"</td>");
        html.append("</tr>");
    }

    public static String tohtml(List<Statistical> statisticals){
        StringBuffer html=new StringBuffer();

        for(int i=0;i<statisticals.size();i++)
            statisticals.get(i).toTd(html);

        return html.toString();

    }
}
