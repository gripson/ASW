package models;

import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pinjie
 * Date: 13-9-1
 * Time: 上午12:52
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Tserial")
public class Serial extends GenericModel {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;


    //之后改进算法。
}
