package com.pega.schooltasklist.database.object;
// Generated Aug 2, 2015 1:47:15 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Groupuser generated by hbm2java
 */
public class Groupuser  implements java.io.Serializable {


     private Long id;
     private Group group;
     private User user;
     private Boolean active;
     private Date createDate;

    public Groupuser() {
    }

	
    public Groupuser(Group group, User user) {
        this.group = group;
        this.user = user;
    }
    public Groupuser(Group group, User user, Boolean active, Date createDate) {
       this.group = group;
       this.user = user;
       this.active = active;
       this.createDate = createDate;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Group getGroup() {
        return this.group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }




}

