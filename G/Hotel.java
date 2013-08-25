package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="Thotel")
//酒店信息
public class Hotel extends GenericModel {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    @Required
    public String hotelName;

    @Required
    @OneToOne
    public Admin admin;//酒店负责人的用户名

    @Required
    public int hotelStar;//酒店星级

    @Required
    public String  hotelAddress;//酒店地址

    @Required
    public double  hotelPrice;//酒店最低售价

    @Required
    public double  hotelReturn;//酒店返还额

    @Required
    public Boolean hotelNightEnabled;//酒店夜间房是否开放

    @Required
    public String telephone;//联系电话

    @Required
    public String fax;//传真

    @Required
    public String details;//酒店详细信息
    //图片也暂时不加上
//    @OneToMany(mappedBy = "hotel")
//    public List<Room> rooms;
}