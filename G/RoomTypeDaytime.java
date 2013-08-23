package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 13-8-23
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Troomtype")
public class RoomTypeDaytime extends GenericModel {
    @Id
    @Column(name="roomtype")
    @MaxSize(11)
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
}
