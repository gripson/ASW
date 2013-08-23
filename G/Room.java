package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pinjie
 * Date: 13-7-17
 * Time: 上午11:07
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Troom")
public class Room extends GenericModel{
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @Required
    public String roomName;//房间名
//    @Required
//    public int roomMoney;
//    @Required
//    public int roomReturnMoney;
//    @Required
//    public int roomRemainNum;//改为RoomDate

    @Required
    public int roomMinNum;//与酒店签订协议时规定的每天至少供给的房间数量

    @Required
    public String introduction;//房间介绍

    @ManyToOne
    public Hotel hotel;
}
