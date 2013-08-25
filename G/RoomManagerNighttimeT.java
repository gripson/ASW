package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

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
