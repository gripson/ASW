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
//日间房房型管理
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
    public String breakfast;//早餐

    @Required
    public String network;

    @Required
    public String loveprice;//房型的售价

    @Required
    public String publicprice;//门市价 目前没有用处

    @Required
    public String commissionrate;//佣金比率

    @Required
    public String fixedcommission;//固定佣金

    @Required
    public String state;//状态

    @Required
    public String area;//面积

    @Required
    public String floor;//楼层

    @Required
    public String bednumber;//床个数
}
