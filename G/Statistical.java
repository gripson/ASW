package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-23
 * Time: 下午7:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Tstatistical")
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
    public String fixedcommission;

    @Required
    public String commissionrate;

    @Required
    public String scheduledtime;

    @Required
    public String departuretime;

    @Required
    public String retentiontime;

    @Required
    public String contact;

    @Required
    public String note;
}
