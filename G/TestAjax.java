package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;
import play.db.jpa.Model;

@Entity
@Table(name="ajax")
public class TestAjax extends JPASupport{
	@Id
    @Required
    @MaxSize(50)
    @MinSize(1)
    @Column(name="id")
    public String id;
	
    @Required
    @MaxSize(50)
    @MinSize(5)
    @Column(name="msgnum")
    public String msgnum;
    
    public String getmsgnum() {
        return msgnum;
    }
 
    public void setmsgnum(String msgnum) {
        this.msgnum = msgnum;
    }
    public TestAjax(String id, String msgnum) {
    	this.id = id;
    	this.msgnum = msgnum;
    
    }
    
}
