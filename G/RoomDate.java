package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Troomprice")
public class RoomDate extends GenericModel {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @Required
    public Date roompriceDate;//日期

    @Required
    public float roompriceNow;//该日期的价格

    @Required
    public float roompriceReturnNow;//该日期的返回额

    @Required
    public float roomNum;//该日期房间配额

    @Required
    public float roomNumNow;//该日期房间剩余量
}
