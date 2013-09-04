package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pinjie
 * Date: 13-7-21
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Tuser")
public class User  extends GenericModel {
    @Id
    @Column(name="phoneNum")
    @MaxSize(11)
    public String phoneNum;

    @Required
    public String userName;

    @Required
    public String password;

}

