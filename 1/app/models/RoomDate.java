package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

@Entity
@Table(name="Troomprice")
public class RoomDate extends GenericModel {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @ManyToOne
    @Required
    public Room room;

    @Required
    public Date date;//日期

    @Required
    public double roompriceNow;//该日期的价格

    @Required
    public double roompriceReturnNow;//该日期的返回额

    @Required
    public int roomNum;//该日期房间配额

    @Required
    public int roomNumNow;//该日期房间剩余量
}
