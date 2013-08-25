package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-23
 * Time: 下午5:13
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TroommanagerdaytimeF")
//日间房余房管理周日至周四
public class RoomManagerDaytimeF extends GenericModel {
    @Id
    @Column(name="roomtype")
    @MaxSize(11)
    public String roomtype;//房型

    @Required
    @ManyToOne
    public Hotel hotel;//酒店ID

    @Required
    public String starttime;//开售时间

    @Required
    public String endtime;//停售时间

    @Required
    public String roomnumber;//房间余量

    @Required
    public String roomprice;//售价
}
