package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="Thotel")
public class Hotel extends GenericModel {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @Required
    public String hotelName;
    @Required
    public String hotelUsername;
    @Required
    public int hotelStar;
    @Required
    public String  hotelAddress;
    @Required
    public double  hotelPrice;
    @Required
    public double  hotelReturn;
    @Required
    public Boolean hotelNightEnabled;
    @Required
    public String telephone;
    @Required
    public String fax;

    public String infraStructure;//基础设施

    public String activeStructure;//活动设施 客房设施不要了

    public String introduction;//酒店介绍

    //图片也暂时不加上
//    @OneToMany(mappedBy = "hotel")
//    public List<Room> rooms;
}