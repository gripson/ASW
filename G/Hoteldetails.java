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

@Entity
@Table(name="Thoteldetails")
public class Hoteldetails {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;
    
    @Required
    @ManyToOne
    public Hotel hotel;
    
    @Required
    public boolean hotelPark;
    
    @Required
    public boolean hotelElevator;
    
    @Required
    public boolean hotelSafe;
   
    @Required
    public boolean hotelRestaurant;
    
    @Required
    public boolean hotelWIFI;
    
    @Required
    public boolean hotelDelivery;
    
    @Required
    public boolean hotelRentalcar;
    
    @Required
    public boolean hotelWake;
    
    @Required
    public boolean hotelWash;
    
    @Required
    public boolean hotelNet;
    
    @Required
    public boolean hotelCheckin;
    
    @Required
    public boolean hotelCheckout;
}
