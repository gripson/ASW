package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.*;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Pinjie
 * Date: 13-8-4
 * Time: 上午11:35
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Torder")
public class Order extends GenericModel{
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @Required
    @OneToOne
    public Hotel hotel;

    @Required
    @OneToOne
    public Room room;

    @Required
    @OneToOne
    public User user;

    @Required
    public int money;

    @Required
    public String name;

    @Required
    public int numOfRoom;

    @Required
    @MaxSize(11)
    @MinSize(11)
    public  String phoneNum;

    @Required
    public Date date;

    @Required
    public int days;

    @Required
    public String remark;

    @Required
    public String state;

    public Date endDate(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(this.date);
        cal.add(Calendar.DATE,days);
        return cal.getTime();
    }
}