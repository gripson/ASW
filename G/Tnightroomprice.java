package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

@Entity
@Table(name = "Tnightroomprice")
public class Tnightroomprice extends GenericModel {


    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id")
    public String id;

    @Required
    @ManyToOne
    public Hotel hotel;

    @Required
    public Date roompriceDate;

    @Required
    public float roompriceNow;

    @Required
    public float floatroompriceReturnNow;

    @Required
    public float nightroomNum;
}
