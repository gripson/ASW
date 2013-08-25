package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-23
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TroommanagerdnighttimeF")
//夜间房管理周日至周四
public class RoomManagerNighttimeF extends GenericModel{
    @Id
    @Column(name="roomtype")
    @MaxSize(11)
    public String roomtype;

    @Required
    @ManyToOne
    public Hotel hotel;

    @Required
    public String starttime;

    @Required
    public String endtime;

    @Required
    public String roomnumber;

    @Required
    public String roomprice;
}
