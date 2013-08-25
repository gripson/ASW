package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-23
 * Time: 下午5:28
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TroommanagerdaytimeT")
//周五至周六日间房管理
public class RoomManagerDaytimeT extends GenericModel {
    @Id
    @Column(name="roomtype")
    @MaxSize(11)
    public String roomtype;

    @Required
    @ManyToOne
    public Hotel hotel;

    @Required
    public String starttime;//开售时间

    @Required
    public String endtime;//停售时间

    @Required
    public String roomnumber;//余房数

    @Required
    public String roomprice;//售价

}
